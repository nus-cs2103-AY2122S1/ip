package tiger.exceptions;

public class TigerStorageLoadException extends TigerException{
    public TigerStorageLoadException(String s) {
        super(String.format("Error encountered in loading the file! Did you alter my memory directly?\nIf you didn't backup my memory, you can try a partial load to see what can be recovered."));
    }
}