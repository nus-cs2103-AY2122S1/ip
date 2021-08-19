public class DukeException extends IllegalArgumentException{
    public DukeException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "@OOPS!!! " + super.getMessage();
    }
}
