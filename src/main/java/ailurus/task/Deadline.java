package ailurus.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ailurus.AilurusException;


public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructor for Ailurus.Deadline tasks
     *
     * @param description description of task /by specific date or time
     */
    public Deadline(String description) throws AilurusException {
        super(description.split("/by", -1)[0].trim());
        String[] arr = description.split("/by", -1);
        handleException(arr);
        try {
            this.by = LocalDate.parse(arr[1].trim());
        } catch (DateTimeParseException e) {
            throw new AilurusException(AilurusException.Error.DATEPARSE);
        }

    }

    @Override
    public void handleException(String... arr) {
        // no description
        if (arr[0].length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYDEADLINE);
        }
        // no /by argument
        if (arr.length <= 1) {
            throw new AilurusException(AilurusException.Error.BY);
        }
        // no /by date
        if (arr[1].length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYBY);
        }
    }

    /**
     * Write data to log task
     *
     * @param writer file writer for writing data to file
     */
    @Override
    public void log(FileWriter writer) {
        try {
            writer.write(String.format("D|%d|%s|%s\n", this.isDone ? 1 : 0, this.description, this.by));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
