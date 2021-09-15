package duke.task;

import duke.util.DukeException;

/**
 * Task without a time tagged to it.
 */
public class ToDo extends Task {

    /**
     * Initialises a ToDo.
     *
     * @param description description of ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    private static void checkFormat(String formattedString) throws DukeException {
        String keyword = formattedString.split(" ", 2)[0];

        if (!keyword.equals("todo")) {
            throw new DukeException("I can't seem to find the todo keyword");
        } else if (formattedString.length() <= 5 || formattedString.substring(5).isEmpty()) {
            //Checks for characters after "todo "
            throw new DukeException("the description of todo cannot be empty");
        }
    }

    /**
     * Creates a ToDo given a ToDo represented as a formatted string.
     * Format: todo [description]
     *
     * @param formattedString ToDo represented as a formatted string.
     * @return Created ToDo
     * @throws DukeException given string fails to meet format requirements
     */
    public static ToDo create(String formattedString) throws DukeException {
        checkFormat(formattedString);
        return new ToDo(formattedString.substring(5).trim());
    }

    @Override
    public String toString() {
        char statusIcon = isDone ? '\u2713' : ' ';
        return String.format("[%c] ToDo: %s", statusIcon, this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ToDo)) {
            return false;
        }


        ToDo toDo = (ToDo) o;
        return isDone == ((ToDo) o).isDone
                && description.equals(toDo.description);
    }
}
