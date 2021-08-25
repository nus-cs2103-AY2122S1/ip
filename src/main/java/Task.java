import exceptions.DukeException;
import exceptions.DukeIllegalFormatException;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Task {
    // TODO: write tests for file methods
    protected String description;
    protected Boolean isDone;
    protected Boolean isSaved = false;

    public Task(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeIllegalFormatException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        // this method does not update db
        this.isDone = true;
    }

    public void save() throws IOException {
        // TODO: raise assertion error if not added to list yet
        // save this task to db
        if (!isSaved) {
            FileWriter fw = new FileWriter(Duke.dataPath, true);
            fw.write(this.toDataString() + "\n");
            fw.close();
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toDataString() {
        return " | " + (this.getStatusIcon().equals("X") ? '1' : '0') + " | " + this.description;
    }
}