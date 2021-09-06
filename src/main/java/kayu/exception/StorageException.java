package kayu.exception;

import kayu.service.TaskList;

/**
 * Manages exceptions generated by {@link kayu.storage.Storage} and {@link TaskList}
 * when accessing files or writing to them.
 */
public class StorageException extends RuntimeException {

    /**
     * Initializes a StorageException instance for use in module.
     *
     * @param message String message to describe error faced.
     */
    public StorageException(String message) {
        super(message);
    }
}
