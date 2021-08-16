public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
