package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.json.simple.JSONObject;

public class Deadline extends Task {

    private String by;

    protected Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    protected Deadline(String description, String by, boolean isCompleted) {
        super(description);
        this.by = by;
        this.isCompleted = isCompleted;
    }

    public static Deadline of(String description, String by) {
        return new Deadline(description, by);
    }

    public static Deadline of(String description, String by, boolean isCompleted) {
        return new Deadline(description, by, isCompleted);
    }


    @Override
    public String toString() throws DateTimeException {
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
