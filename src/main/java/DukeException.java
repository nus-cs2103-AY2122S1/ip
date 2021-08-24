public class DukeException {
    private String toDoError = "    OOPS!!! The description of a todo cannot be empty.";
    private String randomError = "    OOPS!!! I'm sorry, but I don't know what that means :-(";

    public String toDo() {
        return toDoError;
    }

    public String random() {
        return randomError;
    }
}
