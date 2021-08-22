import javax.swing.text.html.Option;
import java.util.Optional;

public class Deadline extends Task{

    private String time;

    private Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public static Deadline of(Optional<String> args) throws IllegalArgumentException {
        // parse args
        String[] parsedArgs = args.orElseThrow(() -> new IllegalArgumentException("☹ OOPS!!! The args of a deadline cannot be empty."))
                                  .split(" /by ");
        if (parsedArgs.length < 2) {
            throw new IllegalArgumentException("☹ OOPS!!! Insufficient args for deadline.");
        }
        return new Deadline(parsedArgs[0], parsedArgs[1]);
    }

    public static Task of(boolean isDone, String description, String time) {
        Task ret = new Deadline(description, time);
        return isDone ? ret.done() : ret;
    }

    public String getTaskType() { return "D"; }

    @Override
    public String toDatabaseString() {
        return String.format("%s|%s", super.toDatabaseString(), this.time);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.getTaskType(), super.toString(), this.time);
    }
}
