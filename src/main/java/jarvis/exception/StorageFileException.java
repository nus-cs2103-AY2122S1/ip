package jarvis.exception;

/**
 * Encapsulates an exception when the storage operation fails.
 */
public class StorageFileException extends JarvisException {
    /**
     * Constructor for StorageFileException.
     *
     * @param errorMessage The error message that should be shown to user.
     */
    public StorageFileException(String errorMessage) {
        super(errorMessage);
    }
}
