package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A deadline is a Task that recognises /by deadline
 *
 * @author Chen Yanyu
 */

class Deadline extends Task {
    public static String deliminator = "/by";
    public static String typeName = "deadline";
    LocalDate time;

    public Deadline(String description, LocalDate time){
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time.format(DateTimeFormatter.ofPattern("MM dd yyyy")) + ")";
    }

    /**
     * return the save string arrays for file writing
     *
     * @return the array of Strings representing the task
     */
    public String[] saveStrings() {
        String isDone = this.getIsDone() ? "1" : "0";
        return new String[]{"D", isDone, this.getDescription(), time.format(DateTimeFormatter.ISO_LOCAL_DATE)};
    }
}
