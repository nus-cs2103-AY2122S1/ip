package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tags;

    /**
     * Constructor.
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        tags = new ArrayList<>();
    }

    /**
     * Returns a status icon.
     *
     * @return X if task is done, an empty space if it is not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public boolean markAsDone() {
        isDone = !isDone;
        return isDone;
    }

    /**
     * Add a tag for a specific task.
     *
     * @param tag the tag to add
     */
    public String addTag(String tag) {
        tags.add(tag);
        return "The tag #" + tag + " has been added to task\n" + description;
    }

    /**
     * Writes to a file using a FileWriter.
     *
     * @param myWriter the given FileWriter
     * @throws IOException ...
     */
    public abstract void writeToFile(FileWriter myWriter) throws IOException;

    @Override
    public String toString() {
        String returnString = "[" + this.getStatusIcon() + "] " + description;
        if (tags.size() == 0) {
            return returnString;
        } else {
            String tagString = "";
            for (int i = 0; i < tags.size(); i++) {
                tagString += " #" + tags.get(i);
            }
            return returnString + tagString;
        }
    }
}
