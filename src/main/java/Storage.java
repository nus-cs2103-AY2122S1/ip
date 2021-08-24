import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Stores data into the file and reads data from the file.
 */
public class Storage {
    /** Path of the current folder as a string */
    private static final String DIRECTORY_PATH = System.getProperty("user.dir");
    /** Path of file containing data saved */
    private static File data;

    /**
     * Constructor of the class `Storage`.
     */
    public Storage() {
        Storage.data = Paths.get(Storage.DIRECTORY_PATH, "data", "duke.txt").toFile();
        this.readFile();
    }
}
