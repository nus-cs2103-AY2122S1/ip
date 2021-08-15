public class ToDo extends Task {

    private String name;
    private boolean completed;

    public ToDo(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[T][X] " + name;
        } else {
            return "[T][ ] " + name;
        }
    }
}
