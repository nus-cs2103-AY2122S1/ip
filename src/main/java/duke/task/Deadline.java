package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Deadline extends Task {
    /**
     * Date and time representation of deadline
     */
    private LocalDateTime by;

    /**
     * Constructor for a `Deadline` task.
     *
     * @param isDone      Indicates whether the task has been completed.
     * @param description Task description.
     * @param by          Date and time representation of deadline.
     */
    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String saveAsData() {
        return 1 + "\n" + super.saveAsData() + "\n" + by.format(INPUT_FORMAT) + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Deadline) {
            Deadline o = (Deadline) obj;

            return super.equals(obj) && this.by.equals(o.by);
        }

        return false;
    }

    @Override
    public boolean checkIfAlreadyAdded(ArrayList<Task> list) {
        boolean hasBeenAdded = false;

        for (Task task : list) {
            if (this.equals(task)) {
                hasBeenAdded = true;
                break;
            }
        }
        return hasBeenAdded;
    }
}
