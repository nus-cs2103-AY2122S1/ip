package duke.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import duke.exception.DukeException;

/**
 * Encapsulates the Storage class which saves and retrieves tasks in Duke to and from file.
 *
 * @author Clifford
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves the save file, otherwise create the directory for the save file.
     *
     * @return file object or null to represent the absence of a save file
     * @throws DukeException if there is an I/O Exception when it is read
     */

    public File retrieveTasks() throws DukeException {
        File file = new File(filePath);
        try {
            if (file.exists()) {
                return file;
            }
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            createStorageFile(file);
            return null;
        }
    }

    /**
     * Creates the directory for the save file.
     *
     * @throws DukeException if there is an IO Exception when it is read
     */
    public void createStorageFile(File file) throws DukeException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e2) {
            throw new DukeException("Something unexpected happened to the file: " + e2.getMessage());
        }
    }

    /**
     * Creates a save file if there is no save file, then save given
     * into the save file, throw DukeException if an IOException
     * occurs during the handling of the file.
     *
     * @param dataText a text representation of the objects to be stored.
     * @throws DukeException if there is an IOException when handling the file
     */
    public void saveTasks(String dataText) throws DukeException {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            createStorageFile(file);
        } finally {
            try {
                FileWriter fw = new FileWriter(filePath);
                fw.write(dataText);
                fw.close();
            } catch (IOException e) {
                throw new DukeException("Something happened to the file when saving: " + e.getMessage());
            }
        }
    }
}
