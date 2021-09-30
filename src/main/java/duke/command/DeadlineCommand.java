package duke.command;

import duke.TaskList;
import duke.task.Deadline;
import java.time.LocalDate;

/**
 * Represents a deadline command.
 */
public class DeadlineCommand extends Command {

    private Deadline deadline;

    public DeadlineCommand(String input) {
        String[] parsed = input.split(" ", 2)[1].split(" /by ");
        assert parsed.length == 2 : "Invalid command entered.";
        String task = parsed[0];
        LocalDate time = LocalDate.parse(parsed[1]);
        this.deadline = new Deadline(task, time);
    }

    /**
     * Returns the result of the execution of the deadline command.
     * @param tasks List of tasks the user has added.
     * @return Result of the execution of the deadline command.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.addTask(this.deadline);
    }
}
