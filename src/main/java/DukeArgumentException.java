public class DukeArgumentException extends IllegalArgumentException{
    public DukeArgumentException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "@OOPS!!! " + super.getMessage();
    }
}
