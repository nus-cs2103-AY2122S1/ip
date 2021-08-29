package duke;

/**
 * Represents a subclass of Task that can be added to the List.
 * It is annotated by E in the list.
 * Contains additional field of time of type String.
 */
public class Event extends Task {
    private String time;

    /**
     * Constructor for Event class.
     *
     * @param name Name of the task.
     * @throws DukeEventException If the name of the task is empty.
     */
    public Event(String name) throws DukeEventException {
        super(name.substring(0, name.indexOf(" /at ") + 1));
        this.time = name.substring(name.indexOf(" /at ") + 5);
        if (name.equals("")) {
            throw new DukeEventException();
        }
    }

    /**
     * Overloaded constructor for Deadline class.
     * Used when loading the data file.
     *
     * @param input input An array of Strings with  type of Task, name, done and time.
     */
    public Event(String[] input) {
        super(input[2].substring(0, input[1].length() - 1), input[1].equals("T") ? true : false);
        this.time = input[3];
    }

    /**
     * Returns the time of the Event Object.
     *
     * @return deadline of the Event Object in String.
     */
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time + ")";
    }

    @Override
    public String toDataString() {
        return "E|" + super.toDataString() + "|" + this.time;
    }
}
