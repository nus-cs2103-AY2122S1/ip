public class Deadline extends Task{
    protected String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = TaskTime.convertDateTimeFormat(dueDate);
    }

    @Override
    public String write() {
        return "D " + super.write() + " | " + this.dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
