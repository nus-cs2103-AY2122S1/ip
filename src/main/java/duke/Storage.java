package duke;

import java.io.File;
import java.io.IOException;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private File data;

    /**
     * Constructor for Storage.
     *
     * @param pathname of the save file.
     * @param dir      directory of the save file.
     * @throws IOException when an IO operations fails.
     */
    public Storage(String pathname, String dir) throws IOException {
        File directory = new File(dir);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File(pathname);
        if (!data.exists()) {
            data.createNewFile();
        }
        this.data = data;
    }

    /**
     * Returns the save file.
     *
     * @return the save file.
     */
    public File load() {
        return this.data;
    }
}
