public class TaskDoesNotExistException extends DukeException{
    public TaskDoesNotExistException(String msg) {
        super(msg);
    }

    @Override
    public void printError() {
        System.out.println("( ⚆ _ ⚆ ) OOPS!!! I cannot find this task! Please select an existing task number.");
    }
}
