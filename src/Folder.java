import java.util.ArrayList;
import java.util.Arrays;

public class Folder extends StorageItem {
    private ArrayList<StorageItem> itemsList;

    /**
     * Constructor for folder
     * Initialize file's items list and sets his name.
     * @param folderName folder's name.
     */
    public Folder(String folderName) {
        super(folderName);
        itemsList = new ArrayList<StorageItem>();
    }

    /**
     * Gets folder size by summing all his items size.
     * Returns folder's size.
     */
    @Override
    public int getSize() {
        int folderSize = 0;
        for (int i = 0; i < this.itemsList.size(); i++)
            folderSize += itemsList.get(i).getSize();
        return folderSize;
    }

    /**
     * Adds item to folder if not already exists.
     * @param item an item to add.
     * Returns false item is already exists in folder and true if added.
     */
    public boolean addItem(StorageItem item) {
        for (int i = 0; i < this.itemsList.size(); i++) {
            if (item.getName() == this.itemsList.get(i).getName())
                return false;
        }
        this.itemsList.add(item);
        return true;
    }
    /**
     * Gets folder's items list.
     * Returns items list of folder.
     */
    public ArrayList<StorageItem> getItemsList() {
        return this.itemsList;
    }

    /**
     * finds file in folder.
     * @param path a path to the wanted file.
     * Returns the file if exist and null if not found.
     */
    public File findFile (String path) {
        ArrayList<String> pathItems =
                new ArrayList<String>(Arrays.asList(path.split("/")));
        ArrayList<StorageItem> wantedItem = this.itemsList;
        for (int i = 0; i < pathItems.size(); i++) {
            for (int j = 0; j < wantedItem.size(); j++) {
                if (pathItems.get(i).equals(wantedItem.get(j).getName())) {
                    if (wantedItem.get(j) instanceof File) {
                        if (i == pathItems.size() - 1)
                            return (File) wantedItem.get(j);
                        return null;
                    }
                    /** Updating inner item list */
                    wantedItem = ((Folder)wantedItem.get(j)).getItemsList();
                    break;
                }
                /** If we are it the last item and not found yet return null */
                else if (j == wantedItem.size() - 1) {
                    return null;
                }
            }
        }
        return null;
    }
}