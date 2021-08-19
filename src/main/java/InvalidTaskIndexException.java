public class InvalidTaskIndexException extends Exception {

    public InvalidTaskIndexException(String str) {
        // calling the constructor of parent Exception
        super(str);
    }


    @Override
    public String toString() {
        return getMessage() ;
    }
}