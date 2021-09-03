package tiger.exceptions.storage;

import tiger.constants.Messages;

public class TigerStorageLoadException extends TigerStorageException {

    public TigerStorageLoadException() {
        super(Messages.EXCEPTION_STORAGE_MEMORY_CORRUPTED.getMessage());
    }
}
