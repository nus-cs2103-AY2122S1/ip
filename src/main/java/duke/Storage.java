package duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Storage {
    private final Path path;
    private final String stringPath;

    public Storage(String filePath) throws InvalidPathException {
        this.path = Path.of(filePath);
        this.stringPath = filePath;
//        if (!isValidPath(path)) {
//            throw new InvalidPathException("Invalid file!! Should end with .txt");
//        }
    }

    /**
     * Method to check if filepath is valid.
     * @param path path to duke.txt
     * @return boolean value if path is a txt file
     */
    private boolean isValidPath(Path path) {
        return path.toString().endsWith(".txt");
    }

    /**
     * Method to create new file if file does not exist.
     * @param directory directory path
     * @param filepath expected file path
     */
    public static void createNewFile(Path directory, Path filepath) {
        if (Files.notExists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Files.notExists(filepath)) {
            try {
                Files.createFile(filepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to return the path.
     * @return path
     */
    public Path load() {
        return this.path;
    }

    /**
     * Method to getPath in String.
     * @return return String representation of path
     */
    public String getPath()  {
        return this.stringPath;
    }
}