import java.io.File;
import java.io.IOException;

public class Storage {
    private File file;

    public Storage(String filePath) {
        File folder = new File(filePath);
        folder.mkdir();
        file = new File(folder, "duke.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public File getFile() {
        return file;
    }
}
