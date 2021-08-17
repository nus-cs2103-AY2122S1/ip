/**
 * Event class: FOr tasks that start at a specific time and ends at a specific time
 * e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description, Type.EVENT);
        this.date = date;
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description + "(at: "
                + date + ")";
    }
}
