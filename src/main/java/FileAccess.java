import java.io.File;

public class FileAccess {
    protected String filePath = "data/duke.txt";
    protected static File f;

    public FileAccess() {
        f = new File(filePath);
        f.mkdirs(); // handle the folder-does-not-exist-yet case
    }

    @Override
    public String toString() {
        return "file path is " + filePath;
    }
}
