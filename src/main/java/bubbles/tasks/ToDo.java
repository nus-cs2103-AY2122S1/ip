package bubbles.tasks;

import bubbles.exceptions.EmptyTaskException;

/**
 * A child class of Task, representing the tasks without any
 * date/time attached to it.
 */
public class ToDo extends Task {
    private ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Acts as a public constructor of a ToDo Object, throw an EmptyTaskException
     * if the input from the user is empty (after the "todo" command).
     *
     * @param input The description of the ToDo.
     * @param isDone Whether the ToDo is done/completed.
     * @return The created ToDo Object.
     * @throws EmptyTaskException Exception thrown when the ToDo description is empty.
     */
    public static ToDo addToDo(String input, boolean isDone) throws EmptyTaskException {
        if (input.equals("")) {
            throw new EmptyTaskException("todo");
        }

        ToDo item = new ToDo(input, isDone);

        return item;
    }

    /**
     * Formats the ToDo Object to store in the hard disk File.
     *
     * @return String representing the ToDo Object (different from the String representation
     *          of the ToDo Task).
     */
    @Override
    public String format() {
        String format = "T | ";

        if (this.isDone) {
            format += "1 | ";
        } else {
            format += "0 | ";
        }

        format += this.description;

        return format;
    }

    /**
     * Returns the String representation of the ToDo.
     *
     * @return The String representation of the ToDo.
     */
    @Override
    public String toString() {
        String res = "[T] [" + this.getStatus() + "] " + this.description;

        return res;
    }
}
