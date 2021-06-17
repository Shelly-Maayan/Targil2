import java.text.ParseException;
import java.util.Date;

public class File extends StorageItem {
    private String ending;
    private String content;

    public File(String fileName, String ending) {
        super(fileName);
        this.content = "";
        this.ending = ending;
    }
    public void addContent(String input) {
        this.content = input;
    }
    @Override
    public int getSize() {
        return content.length();
    }

    public Date getItemDate() {
        return super.getItemDate();
    }

    @Override
    public String getName () {
        return super.getName() + "." + this.ending;
    }

    public void printContent() {
        System.out.println(this.getName() + " Size: " + this.getSize() + "Mb Created: " +
                this.getItemDate());
        System.out.println(this.content);
    }
}
