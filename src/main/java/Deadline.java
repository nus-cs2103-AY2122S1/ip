public class Deadline extends Task {

    private final String by;

    public Deadline(String name, String by) {
        super(name, TaskType.D);
        this.by = by;
    }

    private String deadline() {
        return " (by: " + this.by + ")";
    }

    @Override
    public String details() {
        return super.details() + deadline();
    }

}
