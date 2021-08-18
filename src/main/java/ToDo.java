public class ToDo extends Task {
    public ToDo(String description) throws DukeException {
        super(description);
        if (description == "") {
            throw new DukeException("Looks like you forgot to include a description of the todo.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
