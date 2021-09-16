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
     * Constructor of the toDo class.
     *
     * @throws EmptyFieldException If description is empty.
     */
    public ToDo(String description) throws EmptyFieldException {
        super(description);
        if (description.equals("")) {
            //check for empty description or by
            String errorMessage = "Error! Description or date and time is empty!";
            throw new EmptyFieldException(errorMessage);
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
    public String toFileStringFormat() {
        return "T " + super.toFileStringFormat();
    }

    /**
     * Returns if two ToDo objects are equal based on their description.
     *
     * @param obj The other object to compare to.
     * @return A boolean if the two ToDo objects are equal and false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo) {
            ToDo todo = (ToDo) obj;
            return todo.description.equals(this.description);
        }
        return false;
    }
}
