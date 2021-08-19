/**
 * Event class IS-A Task.
 *
 * Task that has a start and end time
 * @author Timothy Wong Eu-Jin
 * @version Level-4
 */
public class Event extends Task {

    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String toString() {
        return ("[E]" + super.toString() + "(at:" + this.date + ")");
    }

}