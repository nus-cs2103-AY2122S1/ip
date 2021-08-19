package Duke.exception;

public class NoSuchTaskException extends DukeException{
    public NoSuchTaskException(){
        super("Hey, there is no such task!");
    }
}
