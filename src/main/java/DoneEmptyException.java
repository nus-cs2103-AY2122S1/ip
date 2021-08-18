public class DoneEmptyException extends DukeException {
    public DoneEmptyException(String msg) {
        super(msg);
    }

    @Override
    public void printError() {
        System.out.println("( ⚆ _ ⚆ ) OOPS!!! Please enter a task number!");
    }
}
