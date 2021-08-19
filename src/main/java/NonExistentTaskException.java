public class NonExistentTaskException extends DukeException{

    public NonExistentTaskException() {
        super("The task does not exist in the list");
    }

}
