public class Deadline extends Task {

    protected String by;

    public Deadline(String description, int number, String by) {
        super(description, number);
        this.by = by;
    }

    @Override
    public String getTask() {
        return number + "." + this.getTaskNoNum();
    }

    @Override
    public String getTaskNoNum() {
        return "[D]" + super.getTaskNoNum() + "(by: " + by + ")";
    }
}