public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toData() {
        return "T " + super.toData() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}