package tiger.exceptions.storage;

public class TigerStorageInitException extends TigerStorageException {
    // this method should almost never be thrown, unless the user screws around with the file system
    public TigerStorageInitException(String s) {
        super(String.format("Error encounted in initalising/finding storage file!"));
    }
}
