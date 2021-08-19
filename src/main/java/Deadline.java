/**
 * Deadline class IS-A Task.
 *
 * Task that needs to be done by a specific date/time
 * @author Timothy Wong Eu-Jin
 * @version Level-4
 */
public class Deadline extends Task {

    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String toString() {
        return ("[D]" + super.toString() + "(by:" + this.date + ")");
    }

}