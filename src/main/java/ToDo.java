public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String description = super.toString();
        return "[T]" + description;
    }
}
