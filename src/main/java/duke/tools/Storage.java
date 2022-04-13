package duke.tools;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/**
 * Deals with loading duke.tasks from the file and saving duke.tasks in the file.
 * It also creates a new duke.txt file if it does not exist yet.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Storage {
    private final Path path;
    private final String stringPath;

    /**
     * Constructor for Storage.
     * @param filePath Takes in a file path
     * @throws InvalidPathException Throws an invalid path error
     */
    public Storage(String filePath) throws InvalidPathException {
        Storage.createTaskListFile();
        this.path = Path.of(filePath);
        this.stringPath = filePath;
    }

    /**
     * Creates a new file if it does not exist in Duke.
     */
    public static void createTaskListFile() {
        File s = new File("./data");
        boolean isDirectoryCreated;
        try {
            isDirectoryCreated = s.mkdir();
            if (isDirectoryCreated) {
                File f = new File("./data/duke.txt");
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("There is an error");
        }
    }

    /**
     * Returns a path of the Storage file.
     * @return A path.
     */
    public Path load() {
        return this.path;
    }

    /**
     * Returns the String representation of the Storage file's path.
     * @return return String representation of the Storage file's path.
     */
    public String getPath()  {
        return this.stringPath;
    }
}