import javax.swing.text.html.Option;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task{

    private LocalDate time;

    private Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public static Deadline of(Optional<String> args) throws IllegalArgumentException, DateTimeException {
        // parse args
        String[] parsedArgs = args.orElseThrow(() -> new IllegalArgumentException("☹ OOPS!!! The args of a deadline cannot be empty."))
                                  .split(" /by ");
        if (parsedArgs.length < 2) {
            throw new IllegalArgumentException("☹ OOPS!!! Insufficient args for deadline.");
        }
        LocalDate d = LocalDate.parse(parsedArgs[1]);
        return new Deadline(parsedArgs[0], d);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
