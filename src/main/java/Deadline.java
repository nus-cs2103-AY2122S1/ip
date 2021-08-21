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

    public String getTaskType() { return "D"; }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.getTaskType(), super.toString(), this.time);
    }
}
