import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected String at;
    protected LocalDateTime localDateTime;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        setLocalDateAndTime();
        return "[E]" + super.toString() + "(at: " + localDateTime.format(dtf) + ")";
    }

    @Override
    public void displayTask() {
        System.out.println(toString());
    }

    private void setLocalDateAndTime() {
        if (at.substring(0, 1).matches("[0-9]+")) {
            int whitespaceIdx = at.indexOf(" ");
            String date = at.substring(0, whitespaceIdx);
            String time = at.substring(whitespaceIdx+1);
            localDateTime = LocalDateTime.parse(date+"T"+time);
        } else {
            this.localDateTime = LocalDateTime.parse(at, dtf);
        }
    }
}
