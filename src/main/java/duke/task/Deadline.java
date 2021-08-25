package duke.task;

import duke.Duke;

import java.time.LocalDate;

public class Deadline extends Task{
    private LocalDate time;

    public Deadline(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + checkIfTaskCompleted(this) + "] " + this.getTaskName() + this.getTimeString();
    }

    public String getTimeString() {
        return "(by: " + this.time.format(Duke.getFormatter()) + ")";
    }
}
