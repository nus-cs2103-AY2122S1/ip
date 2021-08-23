package tiger.exceptions.storage;

import tiger.constants.Messages;

public class TigerStorageInitException extends TigerStorageException {
    // this method should almost never be thrown, unless the user screws around with the file system
    public TigerStorageInitException(String s) {
        super(String.format(Messages.EXCEPTION_STORAGE_INIT.getMessage()));
    }
}
