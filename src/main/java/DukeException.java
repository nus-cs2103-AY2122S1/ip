public class DukeException {
    private String toDoError = "☹ OOPS!!! The description of a todo cannot be empty.";
    private String randomError = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private String deadlineError = "☹ OOPS!!! Please indicate a valid deadline.";
    private String eventError = "☹ OOPS!!! Please indicate a valid event date.";

    public String toDo() {
        return toDoError;
    }

    public String random() {
        return randomError;
    }

    public String deadline() {
        return deadlineError;
    }

    public String event() {
        return eventError;
    }
}
