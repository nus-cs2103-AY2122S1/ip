public class Todo extends Task{
    public Todo(String description) throws DukeException {
        super(description);
    }
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}
