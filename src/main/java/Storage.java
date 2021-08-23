import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final File storage;


    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.storage = new File(filePath);
        if (!storage.exists()) {
            storage.getParentFile().mkdirs();
            storage.createNewFile();
        }
    }

    public void read(TaskListRequestHandler handler) throws FileNotFoundException {
        Scanner s = new Scanner(storage); // create a Scanner using the File as the source
        while (s.hasNext()) {
            handler.restoreTask(s.nextLine());
        }
    }

    public void write(String s) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(s);
        fw.close();
    }
}
