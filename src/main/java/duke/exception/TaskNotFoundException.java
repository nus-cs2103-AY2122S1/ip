package duke.exception;
public class TaskNotFoundException extends DukeException{
    public TaskNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public void printError() {
        System.out.println("( ⚆ _ ⚆ ) OOPS!!! I cannot find this task! Please select an existing task number.");
    }
}
