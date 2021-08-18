public class DukeEmptyException extends DukeException {
    private String task;
    public DukeEmptyException(String msg, String task) {
        super(msg);
        this.task = task;
    }

    @Override
    public void printError() {
        System.out.printf("( ⚆ _ ⚆ ) OOPS!!! The description of a %s cannot be empty.\n", task);
    }
}
