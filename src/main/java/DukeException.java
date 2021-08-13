public class DukeException extends Exception{

    String errorMessage;

    public DukeException(String s) {
        if (s.equals("empty description")) {
            this.errorMessage = "☹ OOPS!!! The description of a task cannot be empty.";
        }
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
