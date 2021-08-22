public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String status) {
        super(description, status.equals("true"));
    }

    public String getType() {
        return "T";
    }

    public static boolean isValid(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }

        return true;
    }

    public String getDescription() {
        return this.description;
    }

    public String save() {
        return String.format("%s:%s:%s:\n", this.getType(), this.getStatus(), this.description);
    }
}
