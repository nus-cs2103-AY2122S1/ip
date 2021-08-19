public class DukeException extends Exception{
    public DukeException(String e) {
        super(e);
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
