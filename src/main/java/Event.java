import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime at;

    public static Task create(String userInput) throws MalformedCommandException {
        try {
            String[] userInputSplit = userInput.split(" ", 2);
            String[] userParamsSplit = userInputSplit[1].split(" /", 2);
            String description = userParamsSplit[0];
            String at = "";
            if(userParamsSplit[1].startsWith("at")) {
                at = userParamsSplit[1].replaceFirst("at", "").stripLeading();
            }
            return new Event(description, at);
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new MalformedCommandException("Creating an event needs to follow the following format: " +
                "event [description] /at [d/MM/yyyy HHmm]");
        }
    }

    private Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }
}
