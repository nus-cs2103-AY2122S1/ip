package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Duke class which handles interactions with user files for storage purposes.
 */
public class Storage {
    /** Path to storage file. **/
    private final String filePath;

    /**
     * Initializes a new storage container.
     *
     * @param filePath Path to storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the file which stores tasks from the user's session.
     * If the file exists, it returns the file.
     * If the file does not exist, it creates and returns it.
     *
     * @return Text file containing user session data.
     */
    public File getFile() {
        File dataFile = new File(filePath);
        dataFile.getParentFile().mkdirs();
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataFile;
    }

    /**
     * Gets the user task file and writes a string to it.
     * Erases existing data.
     *
     * @param string String to be written.
     */
    public void writeToDataFile(String string) {
        File dataFile = getFile();
        try {
            FileWriter fw = new FileWriter(dataFile);
            fw.write(string);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the user task file and appends a string to it.
     *
     * @param string String to be appended.
     */
    public void appendToDataFile(String string) {
        File dataFile = getFile();
        try {
            FileWriter fw = new FileWriter(dataFile, true);
            fw.write(string);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
