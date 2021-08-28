package duke.task;

import duke.main.DukeException;

public class ToDo extends Task {

    /**
     * Constructor for a ToDo
     *
     * @param description
     */
    public ToDo(String description) {
        super(getDescription(description));
    }

    /**
     * Overloaded constructor for ToDo
     *
     * @param description
     * @param completed
     */
    public ToDo(String description, boolean completed) {
        this(description);
        super.completed = completed;
    }

    private static String getDescription(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("\t☹ OOPS!!! Your todo needs a description.\n");
        }
        return description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the task as a String for storage.
     *
     * @return formatted storage String.
     */
    @Override
    public String storageString() {
        return "T | " + super.completed + " | " + super.description;
    }

}
