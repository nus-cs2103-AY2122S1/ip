import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected String at;
    protected LocalDateTime localDateTime;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        setLocalDateAndTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + "(at: " + localDateTime.format(dtf) + ")";
    }

    @Override
    public void displayTask() {
        System.out.println(toString());
    }

    private void setLocalDateAndTime() {
        int whitespaceIdx = at.indexOf(" ");
        String date = at.substring(0, whitespaceIdx);
        String time = at.substring(whitespaceIdx+1);
        localDateTime = LocalDateTime.parse(date+"T"+time);
    }
}
