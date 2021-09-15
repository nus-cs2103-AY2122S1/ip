package misaki.exceptions;

/**
 * Encapsulates exceptions thrown by the chat bot when storing of data to local storage failed.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class StorageException extends MisakiException {
    public StorageException() {
        super("ERROR: error when saving data to storage!");
    }
}
