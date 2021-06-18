import java.text.ParseException;
import java.util.Date;

public class File extends StorageItem {
    private String ending;
    private String content;

    /**
     * Constructor for file.
     * Initialize file's content and sets his name and ending.
     * @param fileName file's name
     * @param ending file's ending- type of file.
     */
    public File(String fileName, String ending) {
        super(fileName);
        this.content = "";
        this.ending = ending;
    }

    /**
     * Sets file's content.
     * @param input the content to add to file.
     */
    public void addContent(String input) {
        this.content = input;
    }

    /**
     * Gets file's size by counting how many chars in file's content.
     * Returns file's size.
     */
    @Override
    public int getSize() {
        return content.length();
    }

    /**
     * Gets file's creating date.
     * Returns file's creation date.
     */
    public Date getItemDate() {
        return super.getItemDate();
    }

    /**
     * Gets file's full name.
     * Returns file's name.
     */
    @Override
    public String getName () {
        return super.getName() + "." + this.ending;
    }

    /**
     * Prints file's size, creation date and content.
     */
    public void printContent() {
        System.out.println(this.getName() + " Size: " + this.getSize() +
                "Mb Created: " +
                this.getItemDate());
        System.out.println(this.content);
    }
}
