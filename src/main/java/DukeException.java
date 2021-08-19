public class DukeException extends Exception {

    public DukeException(String str) {
        // calling the constructor of parent Exception
        super(str);
    }


    @Override
    public String toString() {
        return getMessage();
    }
}
