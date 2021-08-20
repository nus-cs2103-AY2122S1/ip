public class DukeException extends Exception{

    String errorMessage;

    public DukeException(String s) {
        if (s.equals("empty description")) {
            this.errorMessage = "☹ OOPS!!! The description of a task cannot be empty.";
        } else if (s.equals("index")) {
            this.errorMessage = "☹ OOPS!!! The index of the task is out of range.";
        }
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
