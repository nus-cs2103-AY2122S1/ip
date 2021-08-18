public class DukeException extends Exception {

    public DukeException(String str) {
        // calling the constructor of parent Exception
        super(str);
    }

//    public DukeException() {
//
//    }

    @Override
    public String toString() {
        return getMessage();
    }
}
