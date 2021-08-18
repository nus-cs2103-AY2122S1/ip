public class TaskNoNameException extends DukeException {
    private String task;
    public TaskNoNameException(String msg, String task) {
        super(msg);
        this.task = task;
    }

    @Override
    public void printError() {
        System.out.printf("( ⚆ _ ⚆ ) OOPS!!! Please enter a name for your %s!\n", task);
    }
}
