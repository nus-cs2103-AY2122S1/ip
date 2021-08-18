public class DukeException extends Exception {

    private String dukeError;

    public DukeException(String errorCommand) {
        if (errorCommand.equals("todo")) {
            this.dukeError = "☹ OOPS!!! The description of a todo cannot be empty.";
        }

        if (errorCommand.equals("unknown command")) {
            this.dukeError = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }

    @Override
    public String toString() {
        return this.dukeError;
    }

}
