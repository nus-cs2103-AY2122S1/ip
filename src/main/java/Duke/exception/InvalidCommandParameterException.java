package Duke.exception;

public class InvalidCommandParameterException extends DukeException{
    public InvalidCommandParameterException(){
        super("Oops, you have entered an invalid parameter for this command");
    }
}
