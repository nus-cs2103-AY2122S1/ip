package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.StorageLoadingException;
import duke.exception.StorageSavingException;

/**
 * Represents the class that deals with loading tasks from file and saving tasks to file.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Storage {
    private final String fileDir;
    private final String filePath;

    /**
     * Constructor to initialise a new storage object.
     *
     * @param filePath The path of the txt file that stores the data to be retrieved and to be updated.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileDir = this.filePath.substring(0, this.filePath.lastIndexOf("/") + 1);
    }

    /**
     * Returns the string stored in the storage.
     *
     * @return The string in the storage if it is existent or an empty string otherwise.
     * @throws StorageLoadingException If error encountered during loading.
     */
    public String loadDukeData() throws StorageLoadingException {
        try {
            File storageDir = new File(fileDir);
            File storageFile = new File(filePath);
            boolean isDirExistent = storageDir.exists();
            boolean isFileExistent = storageFile.exists();
            if (!isDirExistent) {
                storageDir.mkdirs();
            }
            if (!isFileExistent) {
                storageFile.createNewFile();
            }
            Scanner scanner = new Scanner(storageFile);
            StringBuilder data = new StringBuilder();
            while (scanner.hasNext()) {
                data.append(scanner.nextLine()).append("\n");
            }
            return data.toString();
        } catch (IOException e) {
            throw new StorageLoadingException();
        }
    }

    /**
     * Saves the existing data in the list into the storage file.
     *
     * @param data The string data to be saved.
     * @throws StorageSavingException If error encountered during saving.
     */
    public void saveDukeData(String data) throws StorageSavingException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new StorageSavingException();
        }
    }
}
