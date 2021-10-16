package ailurus.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ailurus.AilurusException;


public class Event extends Task {
    private final LocalDate at;

    /**
     * Constructor for Event tasks
     *
     * @param description description of task /at specific date or time
     */
    public Event(String description) throws AilurusException {
        super(description.split("/at", -1)[0].trim());
        String[] arr = description.split("/at", -1);
        handleException(arr);
        try {
            this.at = LocalDate.parse(arr[1].trim());
        } catch (DateTimeParseException e) {
            throw new AilurusException(AilurusException.Error.DATEPARSE);
        }
    }

    @Override
    public void handleException(String... arr) {
        // no description
        if (arr[0].length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYEVENT);
        }
        // no /by argument
        if (arr.length <= 1) {
            throw new AilurusException(AilurusException.Error.AT);
        }
        // no /by date
        if (arr[1].length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYAT);
        }
    }

    /**
     * Write data to log task
     *
     * @param writer file writer for writing data to file
     */
    @Override
    public void log(FileWriter writer) throws AilurusException {
        try {
            writer.write(String.format("E☺%d☺%s☺%s\n", this.isDone ? 1 : 0, this.description, this.at));
        } catch (IOException e) {
            throw new AilurusException(AilurusException.Error.LOAD);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
