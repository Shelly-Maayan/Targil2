import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public abstract class StorageItem {
    private String itemName;
    private Date itemDate;

    public long randomDate(String strDate) {
        long milliseconds = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(strDate);
            milliseconds = date.getTime();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return milliseconds;
    }

    public StorageItem(String itemName) {
        this.itemName = itemName;
        long startDate = randomDate("01/01/2017 00:00:00");
        long endDate = randomDate("31/12/2021 23:59:59");
        long rndLong = (Main.rnd.nextLong() % (endDate - startDate)) + startDate;
        this.itemDate = new Timestamp(rndLong);
    }

    public abstract int getSize();

    private void sortFolders(SortingField field, ArrayList<StorageItem> itemsList) {
        Comparator<StorageItem> compareName = Comparator.comparing((StorageItem::getName));

        switch (field.toString()) {
            case "NAME":
                itemsList.sort(compareName);
                break;
            case "SIZE":
                Comparator<StorageItem> compareSize = Comparator.comparing((StorageItem::getSize));
                Comparator<StorageItem> fullCompareSize = compareSize.thenComparing(compareName);
                itemsList.sort(fullCompareSize);
                break;
            case "DATE":
                Comparator<StorageItem> compareDate = Comparator.comparing((StorageItem::getItemDate));
                Comparator<StorageItem> fullCompareDate = compareDate.thenComparing(compareName);
                itemsList.sort(fullCompareDate);
                break;
            default:
                break;
        }
    }
    public void printTree(SortingField field) {
        if (this instanceof File) {
            return; //Print later!!!
        }
        int indent = 0;
        StringBuilder sb = new StringBuilder();
        printDirectoryTree((Folder)this, indent, sb, field);
         System.out.println(sb);
    }

    private static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("|  ");
        }
        return sb.toString();
    }
    private static void printFile(File file, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append(file.getName());
        sb.append("\n");
    }
    public void printDirectoryTree(Folder folder, int indent, StringBuilder sb,
                                   SortingField field) {
        sb.append(getIndentString(indent));
        sb.append(folder.getName());
        sb.append("\n");
        for(int i = 0; i < folder.getItemsList().size(); i++) {
            if(folder.getItemsList().get(i) instanceof Folder) {
                sortFolders(field, ((Folder) folder.getItemsList().get(i)).getItemsList());
                printDirectoryTree((Folder) folder.getItemsList().get(i), indent + 1, sb, field);
            }
            else
                printFile((File)folder.getItemsList().get(i), indent + 1, sb);
        }
    }

    public String getName () {
        return this.itemName;
    }

    public Date getItemDate () {
        return this.itemDate;
    }
}
