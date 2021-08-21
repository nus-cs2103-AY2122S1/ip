package tiger.exceptions.storage;

public class TigerStorageLoadException extends TigerStorageException {
    public TigerStorageLoadException(String s) {
        super("Error encountered in loading the file! Did you alter my memory directly?\nIf you didn't backup my memory, would you like to try a partial load to see what can be recovered? [Y/N]\nPressing N will wipe data currently stored.");
    }
}