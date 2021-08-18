public class WrongArgumentDukeException extends DukeException {
    public WrongArgumentDukeException() {
        super(String.format("The argument is wrong, did you use the required format?."));
    }
}
