import java.time.LocalDateTime;

/**
 * A subclass of Task of deadline type.
 */
public class Deadline extends Task {
    private LocalDateTime datetime;

    Deadline(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
    }

    @Override
    public String showStatus() {
        String status =  super.showStatus();
        return status + " (by: " + datetime.toString().replace('T', ' ') + ")";

    }

    @Override
    public String printType() {
        return "[D]";
    }
}
