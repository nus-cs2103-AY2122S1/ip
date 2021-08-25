import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public static Task create(String userInput) throws MalformedCommandException{
        try {
            String[] userInputSplit = userInput.split(" ", 2);
            String[] userParamsSplit = userInputSplit[1].split(" /", 2);
            String description = userParamsSplit[0];
            String by = "";
            if(userParamsSplit[1].startsWith("by")) {
                by = userParamsSplit[1].replaceFirst("by", "").stripLeading();
            }
            return new Deadline(description, by);
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new MalformedCommandException("Creating an deadline needs to follow the following format: " +
                "deadline [description] /by [d/MM/yyyy HHmm]");
        }

    }

    private Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))+ ")";
    }
}
