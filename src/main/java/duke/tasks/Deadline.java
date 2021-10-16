package duke.tasks;

import duke.utils.DukeDateTime;
import duke.utils.DukeException;

import java.time.DateTimeException;

public class Deadline extends Task {

    /**
     * Instantiates a blank Deadline.
     */
    public Deadline() {
    }

    /**
     * Instantiates a Deadline based on a database entry.
     */
    public Deadline(String entry, boolean done) throws DukeException {
        try {
            String[] args = entry.split("( \\| )", 3);
            if (args.length < 3) {
                throw new DukeException("Error reading DB");
            }
            super.desc = args[0];
            super.isDone = done;
            super.dateTime = DukeDateTime.parse(args[1]);
            if (args.length == 3) {
                super.details = args[2];
            }
        } catch (DateTimeException e) {
            throw new DukeException("Error reading DB");
        }
    }

    /**
     * Adds a time to the deadline.
     *
     * @param rawArgs Argument of the format "DATE_TIME / ADDITIONAL_INFO".
     * @throws DukeException
     */
    public void addTime(String rawArgs) throws DukeException {
        String[] args = rawArgs.split(" / ");
        if (args.length == 0) {
            return;
        }
        try {
            dateTime = DukeDateTime.parse(args[0].trim());
            if (args.length > 1) {
                details = args[1];
            }
        } catch (DateTimeException e) {
            details = rawArgs;
        }
    }

    /**
     * Returns this deadline formatted as a database entry.
     *
     * @return String representing this deadline in database format.
     */
    public String toDatabaseFormat() {
        return String.format("D | %d | %s | %s | %s", super.isDone ? 1 : 0, super.desc,
                dateTime.format(DukeDateTime.Format.DATE_LONG, DukeDateTime.Format.PRINT_TIME), details);
    }

    @Override
    public String toString() {
        String timeSeq = dateTime.format();
        String desc = (details.equals("") ? details : ", " + details);
        return "[D]" + super.toString() + (timeSeq != null ? String.format(" (by: %s)", timeSeq) : "") + desc;
    }
}
