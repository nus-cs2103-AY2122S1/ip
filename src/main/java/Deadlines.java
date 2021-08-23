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
            DateTimeFormatter anotherDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.localDateTime = LocalDateTime.parse(by, anotherDTF);
        } else {
            this.localDateTime = LocalDateTime.parse(by, dtf);
        }
    }
}

