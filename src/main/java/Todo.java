public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
        if (description.isEmpty() || description == "" || description == " ") {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            this.description = description.substring(1);
        }
    }

    @Override
    public String toString() {
        return ("\t[T]" + super.toString());
    }

    @Override
    public String getType() {
        return "todo";
    }
}
