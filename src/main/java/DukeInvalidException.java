public class DukeInvalidException extends DukeException {
    public DukeInvalidException(String msg) {
        super(msg);
    }

    @Override
    public void printError() {
        System.out.println("( ⚆ _ ⚆ ) OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
