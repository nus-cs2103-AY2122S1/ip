package skeltal.task.types;

import skeltal.Parser;
import skeltal.SkeltalException;
import skeltal.task.Task;

/**
 * This class is a child of task that has the additional attribute of time,
 * to allow the storing of deadlines.
 */
public class Deadline extends Task {
    private String time;

    private Deadline(String description) throws SkeltalException {
        super(description);
    }

    /**
     * A factory method that initialises a Deadline object.
     *
     * @param taskAndTime A semi-processed string from the parser which contains
     *                    The task and the time. e.g "Task /time".
     * @throws SkeltalException
     */
    public static Deadline of(String taskAndTime) throws SkeltalException {
        String[] taskTimeArr = Parser.parseDescription(taskAndTime, "deadline");
        String task = taskTimeArr[0];
        String time = taskTimeArr[1];
        Deadline dead = new Deadline(task);
        dead.setTime(time);
        return dead;
    }

    private void setTime(String time) {
        this.time = time;
    }

    private String formatTime() {
        return "(by: " + this.time + ")";
    }

    /**
     * A method that overrides the store() function in the Task parent class,
     * to include the task type and time description of the Deadline object.
     *
     * @return A String representation of the deadline object that is readable by the loader.
     */
    @Override
    public String store() {
        return "D | " + super.store() + " | " + this.time;
    }

    /**
     * Returns a String representation of the Deadline object, for printing purposes.
     * Eg "[D][ ] Task (time)".
     *
     * @return A string representation of the Deadline object for printing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + formatTime();
    }
}
