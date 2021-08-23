import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected String by;
    protected LocalDateTime localDateTime;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        setLocalDateAndTime();
        return "[D]" + super.toString() + "(by: " + localDateTime.format(dtf) + ")";
    }

    @Override
    public void displayTask(){
        System.out.println(toString());
    }

    private void setLocalDateAndTime() {
        if (by.substring(0, 1).matches("[0-9]+")) {
            int whitespaceIdx = by.indexOf(" ");
            String date = by.substring(0, whitespaceIdx);
            String time = by.substring(whitespaceIdx+1);
            localDateTime = LocalDateTime.parse(date+"T"+time);

        } else {
            this.localDateTime = LocalDateTime.parse(by, dtf);
        }
    }
}

