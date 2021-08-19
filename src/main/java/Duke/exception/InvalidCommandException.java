package Duke.exception;

public class InvalidCommandException extends DukeException{
    public InvalidCommandException(){
        super("Oops, unfortunately i am not yet smart enough to understand what you are saying");
    }
}
