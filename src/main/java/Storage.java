import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private final File STORAGE_FILE;

    public Storage(String filename) {
        STORAGE_FILE = new File(filename);
    }

    private void createFilePath(File f) {
        try {
            if (f.getParentFile().mkdirs()) {
                System.out.println("Creating storage directory...");
            }

            if (f.createNewFile()) {
                System.out.println("Creating storage file...");
            }
        } catch (IOException e) {
            System.out.println("Unable to create file.\n" + e);
        }
    }

    public void update(String text) {
        if (!STORAGE_FILE.exists()) {
            createFilePath(STORAGE_FILE);
        }

        try {
            FileWriter fw = new FileWriter(STORAGE_FILE);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to update file.\n" + e);
        }
    }
}
