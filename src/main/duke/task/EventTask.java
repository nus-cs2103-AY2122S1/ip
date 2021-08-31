package duke.task;

import duke.DukeException;

public class EventTask extends Task {

    public EventTask(String description, String time) throws DukeException {
        super(description, time);
    }

<<<<<<< Updated upstream
    public String saveString() {
        return String.format("E|%s", super.saveString());
=======
    /**
     * Format task to be saved in file.
     * @return Formatted string representation of task.
     */
    public String toSaveString() {
        return String.format("E|%s", super.toSaveString());
>>>>>>> Stashed changes
    }

    @Override
    public String toString() {
        String formattedString = super.time.format(TIME_FORMAT);
        return "[E]" + super.toString() + " (at:" + formattedString + ")";
    }
}
