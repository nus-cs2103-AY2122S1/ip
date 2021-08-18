public class ToDo extends Task {

    public ToDo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.title;
    }
}
