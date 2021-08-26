package duke;

import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task {
    public Todo(String description) throws DukeException {
        super(description);
        if(description.strip().equals("")) {
            throw new DukeException("Your todo cannot be empty :(");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public void writeToFile(FileWriter myWriter) throws IOException {
        myWriter.write("todo" + description);
    }
}