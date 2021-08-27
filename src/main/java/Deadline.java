public class Deadline extends Task {

    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String getStatus(){
        return "D" + super.getStatus() + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "D" + " | " + super.toString() + " | " + by;
    }
}