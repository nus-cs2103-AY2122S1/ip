import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime timing;

    public Deadline(String description) throws DukeException {
        super(description);
        if (description.strip().equals("")) {
            throw new DukeException("Your Deadline cannot be empty :(");
        }

        int index = description.indexOf("/by");

        timing = LocalDateTime.parse(description.substring(index + 3).strip(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        this.description = description.substring(0, index);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + timing.format(DateTimeFormatter.ofPattern("MMM d yyy HH:mm")) + ")";
    }

    public void writeToFile(FileWriter myWriter) throws IOException {
        myWriter.write("deadline" + description + "/by "
                + timing.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));

    }
}