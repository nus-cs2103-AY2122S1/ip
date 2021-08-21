import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;
    private String time;

    public Deadline(String name, LocalDate by, String time) {
            super(name);
            this.by = by;
            this.time = time;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by: "
                    + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + time + ")";
        }
    }
