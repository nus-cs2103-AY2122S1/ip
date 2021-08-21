import javax.swing.text.html.Option;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;

public class Event extends Task{

    private LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public static Event of(Optional<String> args) throws IllegalArgumentException, DateTimeException {
        // parse args
        String[] parsedArgs = args.orElseThrow(() -> new IllegalArgumentException("☹ OOPS!!! The args of a event cannot be empty."))
                                  .split(" /at ");
        if (parsedArgs.length < 2) {
            throw new IllegalArgumentException("☹ OOPS!!! Insufficient args for event.");
        }
        LocalDate d = LocalDate.parse(parsedArgs[1]);
        return new Event(parsedArgs[0], d);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }
}
