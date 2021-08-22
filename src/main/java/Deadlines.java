import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    protected String by;
    protected LocalDateTime localDateTime;

    public Deadlines(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        setLocalDateAndTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + "(by: " + localDateTime.format(dtf) + ")";
    }

    @Override
    public void displayTask(){
        System.out.println(toString());
    }

    private void setLocalDateAndTime() {
        int whitespaceIdx = by.indexOf(" ");
        String date = by.substring(0, whitespaceIdx);
        String time = by.substring(whitespaceIdx+1);
        localDateTime = LocalDateTime.parse(date+"T"+time);
    }
}

