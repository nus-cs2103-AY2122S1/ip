package duke.task;

import duke.exception.EmptyFieldException;

/**
 * Represents to-dos (a type of tasks) that Duke can keep track of.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class ToDo extends Task {
    /**
     * A string representing the description of a to-do.
     *
     * @throws EmptyFieldException If description is empty.
     */
    public ToDo(String description) throws EmptyFieldException {
        super(description);
        if (description.equals("")) {
            //check for empty description or by
            throw new EmptyFieldException("Error! Description or date and time is empty!");
        }
    }

    /**
     * Returns a string that is used to represent to-dos when saved to a file.
     *
     * @return A string representation of a to-do for saving to files.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string that is used to represent to-dos when Duke is interacting with a user.
     *
     * @return A string representation of a to-do to be displayed to users.
     */
    @Override
    public String saveToFile() {
        return "T " + super.saveToFile();
    }
}
