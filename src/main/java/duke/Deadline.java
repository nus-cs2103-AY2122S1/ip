package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a task that have a deadline which is a string.
 * @author Dominic Siew Zhen Yu
 *
 */

public class Deadline extends Task {
    private String deadline;
    private String taskIndicator = "[D]";

    /**
     * The constructor for the Deadlines class with the userInput (which refers to the name of the task)
     * and the deadline parameter which is the deadline of the deadlines task.
     *
     * @param userInput the name of the deadlines
     * @param deadline the time you have to complete the Deadlines
     */

    public Deadline(String userInput, String deadline, boolean newInput) {
        super(userInput);

        if (newInput) {
            LocalDate date = LocalDate.parse(deadline);
            this.deadline = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            this.deadline = deadline;
        }

    }

    /**
     * the printName() method prints the name of the deadlines task.
     *
     * @return the string representation of the deadlines
     */

    public String printName() {
        return taskIndicator + " " + super.printName() + " (by: " + this.deadline + ")";
    }
}
