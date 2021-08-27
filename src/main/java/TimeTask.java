import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class TimeTask extends Task{

    private LocalDate time;


    public TimeTask(String description) {
        super(description);
    }

    public TimeTask(String description, boolean isDone) {
        super(description, isDone);
    }

    public TimeTask(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public TimeTask(String description, String timeString) {
        super(description);
        this.time = parseTimeString(timeString);
    }

    public TimeTask(String description, boolean isDone, String timeString) {
        super(description, isDone);
        this.time = parseTimeString(timeString);
    }

    protected static LocalDate parseTimeString(String s) {
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException("the inputted time format is invalid, please enter as yyyy-mm-dd");
        }
    }

    public LocalDate getTime() {
        return this.time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
}
