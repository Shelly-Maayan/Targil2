import java.util.ArrayList;
import java.util.Comparator;

public enum SortingField {
    itemName {
        public void SortItems (ArrayList<? extends StorageItem> itemsList) {
            Comparator<? extends StorageItem> nameComperator = Comparator.comparing();

            itemsList.sort(nameComperator);

        }

    }
}
