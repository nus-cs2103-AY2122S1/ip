package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Storage class encapsulates the process of storing and loading files to and from the hard disk.
 *
 * @author Quan Teng Foong
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves previously stored data at filePath. If filePath does not exist, an empty file is created.
     *
     * @return file at filePath
     * @throws IOException if unable to create new file
     */
    public File retrieve() throws IOException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                mkParentDir();
            }
            file.createNewFile();
        }
        return file;
    }

    /**
     * Makes a parent directory for the current filePath. If parent does not have a parent, this method
     * recursively calls itself.
     */
    private void mkParentDir() {
        File file = new File(this.filePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            if (!parentFile.getParentFile().exists()) {
                new Storage(file.getParent()).mkParentDir();
            }
            parentFile.mkdir();
        }
    }

    /**
     * Stores the given TaskList into filePath.
     *
     * @param taskList the current TaskList
     * @throws IOException if there is an error writing to the fileIO
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        fileWriter.write(taskList.toStorage());
        fileWriter.close();
    }

    /**
     * Deletes the storage file at filePath.
     */
    public void clear() {
        File file = new File(this.filePath);
        file.delete();
    }
}
