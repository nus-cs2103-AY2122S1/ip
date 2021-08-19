public class InvalidFormatException extends Exception{

    public InvalidFormatException(String str) {
        // calling the constructor of parent Exception
        super(str);
    }


    @Override
    public String toString() {
        return getMessage() ;
    }
}
