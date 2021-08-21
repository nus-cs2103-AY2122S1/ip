package tiger.exceptions.storage;

public class TigerStorageSaveException extends TigerStorageException {
    // this method should almost never be thrown, unless the user screws around with the file system
    public TigerStorageSaveException(String s) {
        super(String.format("Error encountered in saving the file! Be sure you don't have the file open while writing!"));
    }
}