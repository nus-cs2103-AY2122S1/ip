package tiger.exceptions;

public class TigerStorageInitException extends TigerException{
    // this method should almost never be thrown, unless the user screws around with the file system
    public TigerStorageInitException(String s) {
        super(String.format("Error encounted in initalising/finding storage file!"));
    }
}
