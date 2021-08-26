public class Todo extends Task {
    public Todo(String desc) throws DukeException.EmptyDescriptionException {
        super(desc);
    }

    public Todo(boolean isComplete, String desc) throws DukeException.EmptyDescriptionException {
        super(isComplete, desc);
    }

    @Override
    public String getRepr() {
        return String.format("T|%s|null", super.getRepr());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
