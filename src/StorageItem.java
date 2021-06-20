import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public abstract class StorageItem {
    private String itemName;
    private Date itemDate;
    private static int count_files = 0;

    /**
     * Converting a date to milli seconds from 01/01/1970.
     * @param strDate a date in string form.
     * Returns a date in a milii seconds form.
     */
    public long dateToMillis(String strDate) {
        long milliseconds = 0;
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(strDate);
            milliseconds = date.getTime();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return milliseconds;
    }

    /**
     * Returns absolute value of a given number
     * @param number a long type number
     */
    private long abs(long number) {
        if(number < 0)
            return -number;
        return number;
    }

    /**
     * Constructor for storage item.
     * Initialize items name and generate random creation date.
     * @param itemName storage item's name (type String).
     */
    public StorageItem(String itemName) {
        this.itemName = itemName;
        long startDate = dateToMillis("2017/01/01 00:00:00");
        long endDate = dateToMillis("2021/12/31 23:59:59");
        long rndLong =
                (abs(Main.rnd.nextLong() % (endDate - startDate)) + startDate);
        this.itemDate = new Timestamp(rndLong);
    }
    /**
     * Gets size of storage item.
     */
    public abstract int getSize();

    /**
     * Sorts folder by requested method.
     * @param field a method to sort the folders by.
     * @param itemsList the items to sort.
     */
    private void sortFolder(SortingField field,
                            ArrayList<StorageItem> itemsList) {
        Comparator<StorageItem> compareName =
                Comparator.comparing((StorageItem::getName));

        switch (field.toString()) {
            case "NAME":
                itemsList.sort(compareName);
                break;
            case "SIZE":
                Comparator<StorageItem> compareSize =
                        Comparator.comparing((StorageItem::getSize));
                Comparator<StorageItem> fullCompareSize =
                        compareSize.thenComparing(compareName);
                itemsList.sort(fullCompareSize);
                break;
            case "DATE":
                Comparator<StorageItem> compareDate =
                        Comparator.comparing((StorageItem::getItemDate));
                Comparator<StorageItem> fullCompareDate =
                        compareDate.thenComparing(compareName);
                itemsList.sort(fullCompareDate);
                break;
            default:
                break;
        }
    }

    /**
     * Sorts the storage items and prints them in a tree struct.
     * @param field a method to sort by.
     */
    public void printTree(SortingField field) {
        if (this instanceof File) {
            return;
        }
        sortExternalFolder((Folder) this, field);
        int indent = 0;
        StringBuilder printString = new StringBuilder();
        printDirectoryTree((Folder)this, indent, printString, field);
        System.out.println(printString);
    }

    /**
     * Sorts the first folder
     * @param folder the folder to sort.
     * @param field a method to sort by.
     */
    private void sortExternalFolder(Folder folder, SortingField field)
    {
        sortFolder(field, folder.getItemsList());
    }

    /**
     * Gets a string with indents and symbols that matches the printing format.
     * @param indent amount of indents and symbols to print.
     * Returns the string to print.
     */
    private static String getIndentString(int indent) {
        StringBuilder printString = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            printString.append("|  ");
        }
        return printString.toString();
    }

    /**
     * Prints indents and file's name.
     * @param file the wanted file to print.
     * @param indent amount of indents and symbols to print.
     * @param printString the string to print by printing format.
     */
    private static void printFile(File file, int indent,
                                  StringBuilder printString) {
        printString.append(getIndentString(indent));
        printString.append(file.getName());
        printString.append("\n");
    }

    /**
     * Prints all storage items in order.
     * @param folder the folder to print
     * @param indent amount of indents and symbols to print.
     * @param printString the string to print by printing format.
     * @param field a method to sort by.
     */
    public void printDirectoryTree(Folder folder, int indent,
                                   StringBuilder printString,
                                   SortingField field) {

        printString.append(getIndentString(indent));
        printString.append(folder.getName());
        printString.append("\n");
        for(int i = 0; i < folder.getItemsList().size(); i++) {
            if(folder.getItemsList().get(i) instanceof Folder) {
                sortFolder(field,
                        ((Folder) folder.getItemsList().get(i)).getItemsList());
                printDirectoryTree((Folder) folder.getItemsList().get(i),
                        indent + 1, printString, field);
            }
            else {
                printFile((File) folder.getItemsList().get(i),
                        indent + 1, printString);
            }

        }
    }

    /**
     * Gets storage item's name.
     */
    public String getName () {
        return this.itemName;
    }

    /**
     * Gets storage item's creation date.
     */
    public Date getItemDate () {
        return this.itemDate;
    }
}