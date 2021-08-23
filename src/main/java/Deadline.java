public class Deadline extends Task {

    private final String by;

    Deadline(String name, String by, boolean isCompleted) {
        super(name, TaskType.D, isCompleted);
        this.by = by;
    }

    private String deadline() {
        return " (by: " + this.by + ")";
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public Task updateName(String input) {
        return new Deadline(input, this.by, this.getCompleted());
    }

    @Override
    public Task complete() {
        return new Deadline(this.getName(), this.by, true);
    }

    @Override
    public String details() {
        String checkbox = "[" + ( getCompleted() ? "X" : " ") + "]";
        String details = taskTypeString() + checkbox + " " + this.getName();
        return details + deadline();
    }

    @Override
    public String getLabel() {
        return this.getName() + "|" + this.getBy();
    }

}
