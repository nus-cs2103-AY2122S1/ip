public class RepeatedTaskException extends DukeException{

    public RepeatedTaskException() {
        super("The above task already exists in the list");
    }

}
