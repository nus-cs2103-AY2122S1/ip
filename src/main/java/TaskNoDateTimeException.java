public class TaskNoDateTimeException extends DukeException {
    private String task;
    public TaskNoDateTimeException(String msg, String task) {
        super(msg);
        this.task = task;
    }

    @Override
    public void printError() {
        System.out.printf("( ⚆ _ ⚆ ) OOPS!!! Please enter a date/time for your %s!\n", task);
    }
}
