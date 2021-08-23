/**
 * Deadline class: For tasks that need to be done before a specific date/time
 * e.g., submit report by 11/10/2019 5pm
 */
public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description, Type.DEADLINE);
        this.date = date;
    }

    public Deadline(boolean isDone, String description, String date) {
        super(isDone, description, Type.DEADLINE);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description.trim() + " (by: "
                + date + ")";
    }
}
