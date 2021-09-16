package duke.task;

import duke.main.DukeException;

import java.util.List;

/**
 * Encapsulates a task to be done.
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo
     *
     * @param description String for the Todo.
     */
    public ToDo(String description) {
        super(getDescription(description));
    }

    /**
     * Overloaded constructor for ToDo
     *
     * @param description String for the ToDo.
     * @param completed   boolean true if ToDo is completed; else false.
     * @param tags        List of tags associated with the ToDo.
     */
    public ToDo(String description, boolean completed, List<String> tags) {
        this(description);
        super.isComplete = completed;
        super.tags = tags;
    }

    private static String getDescription(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("\t OOPS!!! Your todo needs a description.\n");
        }
        return description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }

    /**
     * Formats the task as a String for storage.
     *
     * @return formatted storage String.
     */
    @Override
    public String generateStorageString() {
        return "T | " + super.isComplete + " | " + super.description + " | " + super.formatTags() + " | " + " ";
    }

}
