package tiger.exceptions.storage;

import tiger.messages.Messages;

public class TigerStorageSaveException extends TigerStorageException {
    // this method should almost never be thrown, unless the user screws around with the file system
    public TigerStorageSaveException(String s) {
        super(String.format(Messages.EXCPETION_STORAGE_SAVE.getMessage()));
    }
}