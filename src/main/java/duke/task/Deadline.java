package duke.task;

import duke.command.WrongCommand;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    private String by;

    /**
     * Default constructor for a Deadline.
     *
     * @param description The description of the Deadline.
     * @param by The deadline of the Deadline task.
     */
    protected Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * A constructor for a Deadline which includes a boolean isCompleted.
     *
     * @param description The description of the Deadline.
     * @param by The deadline of the Deadline task.
     * @param isCompleted Whether the Deadline has been completed.
     */
    protected Deadline(String description, String by, boolean isCompleted) {
        super(description);
        this.by = by;
        this.isCompleted = isCompleted;
    }

    /**
     * Default factory method for a Deadline.
     *
     * @param description The description of the Deadline.
     * @param by The deadline of the Deadline task.
     * @return A new Deadline with the given description.
     */
    public static Deadline of(String description, String by) {
        return new Deadline(description, by);
    }

    /**
     * A factory method for a Deadline which includes a boolean isCompleted.
     *
     * @param description The description of the Deadline.
     * @param by The deadline of the Deadline task.
     * @param isCompleted Whether the Deadline has been completed.
     * @return A new Deadline with the given description.
     */
    public static Deadline of(String description, String by, boolean isCompleted) {
        return new Deadline(description, by, isCompleted);
    }


    @Override
    public String toString() {
        String[] date = by.split(" ", 2);
        LocalDate ld = LocalDate.parse(date[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String formattedDate = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        LocalTime lt = LocalTime.parse(date[1], DateTimeFormatter.ofPattern("HHmm"));
        String formattedTime = lt.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return String.format("[D]%s %s %s", isCompleted ? "[X]" : "[ ]", description,
                "(by: " + formattedDate + ", " + formattedTime + ")");
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("type", "deadline");
        obj.put("description", description);
        obj.put("isCompleted", isCompleted);
        obj.put("by", by);
        return obj;
    }
}
