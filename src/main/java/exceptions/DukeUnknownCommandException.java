package exceptions;

public class DukeUnknownCommandException extends DukeException{

    public DukeUnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
