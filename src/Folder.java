import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Folder extends StorageItem {
    private ArrayList<StorageItem> itemsList;

    public Folder(String folderName) {
        super(folderName);
        itemsList = new ArrayList<StorageItem>();
    }

    @Override
    public int getSize() {
        int folderSize = 0;
        for (int i = 0; i < this.itemsList.size(); i++)
            folderSize += itemsList.get(i).getSize();
        return folderSize;
    }

    public boolean addItem(StorageItem item) {
        for (int i = 0; i < this.itemsList.size(); i++) {
            if (item.getName() == this.itemsList.get(i).getName())
                return false;
        }
        this.itemsList.add(item);
        return true;
    }

    public ArrayList<StorageItem> getItemsList() {
        return this.itemsList;
    }

    public void print_folder(){
        System.out.println(this.getName() + ":");
        for(int i=0; i<this.itemsList.size(); i++)
        {
            System.out.println(this.itemsList.get(i).getName());
        }
        System.out.println("-------");

    }
    public File findFile (String path) {
        ArrayList<String> pathItems = new ArrayList<String>(Arrays.asList(path.split("/")));
        ArrayList<StorageItem> wantedItem = this.itemsList;
        for (int i = 0; i < pathItems.size(); i++) {
            for (int j = 0; j < wantedItem.size(); j++) {
                //String name = wantedItem.get(j).getName();
                //String name2 = pathItems.get(i);
                if (pathItems.get(i).equals(wantedItem.get(j).getName())) {
                    if (wantedItem.get(j) instanceof File) {
                        if (i == pathItems.size() - 1)
                            return (File) wantedItem.get(j);
                        return null;
                    }
                    wantedItem = ((Folder)wantedItem.get(j)).getItemsList();
                    break;
                }
                else if (j == wantedItem.size() - 1) {
                    return null;
                }
            }
        }
        return null;
    }
}
