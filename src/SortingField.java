public enum SortingField {
    /**
     * Matches each sorting type to its string
     */
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