public class Deadline extends Task {

    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.taskType = TaskType.D;
        this.date = date;
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += String.format(" (by: %s)", this.date);
        return str;
    }

    @Override
    public String getDataString() {
        return String.format("%s_~_%s_~_%s_~_%s", this.taskType, this.getStatusInt(), this.description, this.date);
    }
}
