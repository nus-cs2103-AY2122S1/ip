package duke;

import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {

    String timing;

    public Event(String description) throws DukeException {
        super(description);
        if(description.strip().equals("")) {
            throw new DukeException("Your Event cannot be empty :(");
        }
        int index = description.indexOf("/at");
        if (index == -1) {
            throw new DukeException("Please use the correct format! event <name> /at <text>");
        }
        timing = description.substring(index + 3);
        this.description = description.substring(0, index - 1);

    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + timing + ")";
    }

    public void writeToFile(FileWriter myWriter) throws IOException {
        myWriter.write("event" + description + " /at" + timing);
    }
}
