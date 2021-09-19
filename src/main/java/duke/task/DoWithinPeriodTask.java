package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class DoWithinPeriodTask extends Task {
    private final LocalDateTime deadlineStart;
    private final LocalDateTime deadlineEnd;

    /**
     * Constructor to create a Deadline Task
     * @param description The String explaining what needs to be done by the Task
     * @param deadlineStart The starting due date and time of the Task
     * @param deadlineEnd The ending due date and time of the Task
     */
    public DoWithinPeriodTask(String description, String deadlineStart, String deadlineEnd) throws DukeException {
        super(description);
        this.deadlineStart = LocalDateTime.parse(deadlineStart, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        this.deadlineEnd = LocalDateTime.parse(deadlineEnd, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        if (!(this.deadlineStart.isBefore(this.deadlineEnd))) {
            throw new DukeException("Incorrect Format of the DoWithinPeriod Command!!, \nCorrect Format --> "
                    + "do_within_period <Description> /between <dd/MM/yyyy HHmm> and\n"
                    + "<dd/MM/yyyy HHmm>");
        }
    }

    /**
     * Constructor to create a Deadline Task
     * @param isCompleted The parameter indicates whether the task has been completed
     * @param deadlineStart The starting due date and time of the Task
     * @param deadlineEnd The ending due date and time of the Task
     */
    public DoWithinPeriodTask(String isCompleted, String description, String deadlineStart, String deadlineEnd) throws
            DukeException {
        super(isCompleted, description);
        this.deadlineStart = LocalDateTime.parse(deadlineStart, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        this.deadlineEnd = LocalDateTime.parse(deadlineEnd, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        if (!(this.deadlineStart.isBefore(this.deadlineEnd))) {
            throw new DukeException("Incorrect Format of the DoWithinPeriod Command!!, \nCorrect Format --> "
                    + "do_within_period <Description> /between <dd/MM/yyyy HHmm> and\n"
                    + "<dd/MM/yyyy HHmm>");
        }
    }

    public String getDeadlineStart() {
        return this.deadlineStart.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public String getDeadlineEnd() {
        return this.deadlineEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String writeToFile() {
        return String.format("DO_WITHIN_PERIOD | %s | %s | %s | %s\n", getIsCompleted(), getDescription(),
                getDeadlineStart(), getDeadlineEnd());
    }

    @Override
    public String toString() {
        return String.format("[P]%s (between: %s and %s)", super.toString(),
                deadlineStart.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")),
                deadlineEnd.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")));
    }
}

