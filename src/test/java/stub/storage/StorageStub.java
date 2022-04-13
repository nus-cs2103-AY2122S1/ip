package stub.storage;

import exception.ErrorAccessingFileException;
import storage.Storage;
import storage.StorageFile;

/**
 * Encapsulates storage for test data.
 */
public class StorageStub extends Storage {
    /**
     * Loads file containing test data.
     *
     * @return `StorageFile`.
     */
    public StorageFile loadListFile() {
        try {
            StorageFile file = super.loadListFile("test.txt");
            return file;
        } catch (ErrorAccessingFileException e) {
            e.printStackTrace();
        }

        return null;
    }
}
