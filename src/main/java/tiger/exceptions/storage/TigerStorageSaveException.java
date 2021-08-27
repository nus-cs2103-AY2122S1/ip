package tiger.exceptions.storage;

import tiger.constants.Messages;

public class TigerStorageSaveException extends TigerStorageException {
    // this method should almost never be thrown, unless the user screws around with the file system
    public TigerStorageSaveException(String s) {
        super(String.format(Messages.EXCEPTION_STORAGE_SAVE.getMessage()));
    }
}