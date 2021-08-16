import javax.swing.text.html.Option;
import java.util.Optional;

public class Event extends Task{

    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public static Event of(Optional<String> args) throws IllegalArgumentException {
        // parse args
        String[] parsedArgs = args.orElseThrow(() -> new IllegalArgumentException("☹ OOPS!!! The args of a event cannot be empty."))
                                  .split(" /at ");
        if (parsedArgs.length < 2) {
            throw new IllegalArgumentException("☹ OOPS!!! Insufficient args for event.");
        }
        return new Event(parsedArgs[0], parsedArgs[1]);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}
