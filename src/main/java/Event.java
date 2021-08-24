import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate at;

    /**
     * Constructor for Event tasks
     *
     * @param description description of task /at specific date or time
     */
    public Event(String description) {
        super(description.split("/at", -1)[0].trim());
        String[] arr = description.split("/at", -1);
        if (arr[0].length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYEVENT);
        } else if (arr.length <= 1) {
            throw new AilurusException(AilurusException.Error.AT);
        } else if (arr[1].length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYAT);
        } else {
            this.at = LocalDate.parse(arr[1].trim());
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
