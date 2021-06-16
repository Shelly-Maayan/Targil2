import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class StorageItem {
    private String itemName;
    private Date itemDate;

    public long randomDate(String strDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = dateFormat.parse(strDate);
        return date.getTime();
    }

    public StorageItem(String itemName) throws ParseException {
        this.itemName = itemName;
        long startDate = randomDate("01/01/2017 00:00:00");
        long endDate = randomDate("31/12/2021 23:59:59");
        long rndLong = (Main.rnd.nextLong() % (endDate - startDate)) + startDate;
        this.itemDate = new Timestamp(rndLong);
    }

    public abstract int getSize();


    public void printTree(SortingField field) {
        String separator = "|    ";
        int counter = 0;
        if (this instanceof File)
            System.out.println(this.getName());
        else {
            for (int i = 0; i < ((Folder)this).getItemsList().size(); i++) {

            }
        }
    }

    public String getName () {
        return this.itemName;
    }

    public Date getItemDate () {
        return this.itemDate;
    }
}
