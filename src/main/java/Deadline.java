import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {

    String timing;

    public Deadline(String description) throws DukeException {
        super(description);
        if(description.strip().equals("")) {
            throw new DukeException("Your Deadline cannot be empty :(");
        }
        int index = description.indexOf("/by");
        timing = description.substring(index + 3);
        this.description = description.substring(0, index - 1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + timing + ")";
    }

    public void writeToFile(FileWriter myWriter) throws IOException {
        myWriter.write("deadline" + description + " /by" + timing);
    }
}