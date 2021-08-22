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

    public static Task of(boolean isDone, String description, String time) {
        Task ret = new Event(description, time);
        return isDone ? ret.done() : ret;
    }

    @Override
    public String getTaskType() { return "E"; }

    @Override
    public String toDatabaseString() {
        return String.format("%s|%s", super.toDatabaseString(), this.time);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", this.getTaskType() , super.toString(), this.time);
    }
}
