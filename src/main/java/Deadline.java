import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

import org.json.simple.JSONObject;

public class Deadline extends Task {

    private String by;
    private String formattedDate;
    private String formattedTime;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, String by, boolean isCompleted) {
        super(name);
        this.by = by;
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        String[] date = by.split(" ",2);
        LocalDate ld = LocalDate.parse(date[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        formattedDate = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        LocalTime lt = LocalTime.parse(date[1], DateTimeFormatter.ofPattern("HHmm"));
        formattedTime = lt.format(DateTimeFormatter.ofPattern("hh:mm a"));
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
