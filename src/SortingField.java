import java.util.ArrayList;
import java.util.Comparator;

public enum SortingField {
    NAME {
        @Override
        public String toString() {
            return "NAME";
        }
    },
    SIZE {
        @Override
        public String toString() {
            return "SIZE";
        }
    },
    DATE {
        @Override
        public String toString() {
            return "DATE";
        }
    }
}
