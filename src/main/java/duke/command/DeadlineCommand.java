package duke.command;

import java.time.LocalDateTime;

import duke.exception.InvalidInputException;
import duke.exception.NoActionException;
import duke.exception.NoTimeException;
import duke.exception.SaveFileException;
import duke.task.Deadline;
import duke.task.Task;
import duke.util.Parser;
import duke.util.Reply;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command class encapsulating the logic that occurs when the user issues a 'deadline' command.
 */
public class DeadlineCommand extends Command {
    private final String action;

    /**
     * Constructor for the Deadline Command.
     *
     * @param action String input for the deadline task
     */
    public DeadlineCommand(String action) {
        super(false);
        this.action = action;
    }

    /**
     * Creates and adds a 'deadline' task to the tasks
     *
     * @param tasks List of existing tasks
     * @param storage Storage class handling the persistence of the tasks
     * @return CommandResult of the encapsulating the effects of the command after it completes
     * @throws InvalidInputException if input cannot be parsed into a date
     * @throws NoActionException if no todo list action given
     * @throws NoTimeException if no deadline is provided
     * @throws SaveFileException if there are issues with the save file
     */

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws InvalidInputException,
            NoActionException, NoTimeException, SaveFileException {
        assert tasks != null;
        assert storage != null;
        String[] deadlineInputs = action.split("/by", 2);
        if (deadlineInputs[0].length() == 0) {
            throw new NoActionException("Command 'deadline' requires a task action");
        }
        if (deadlineInputs.length <= 1) {
            throw new NoTimeException(
                    "Command 'deadline' requires a deadline to be specified. Use /by to specify a deadline.");
        }
        LocalDateTime deadline = Parser.parseDate(deadlineInputs[1].trim());
        Task newTask = new Deadline(deadlineInputs[0].trim(), deadline);
        tasks.add(newTask);
        storage.save(tasks);
        return new CommandResult(Reply.showTaskAdded(newTask, tasks), true, super.isExit());
    }

    /**
     * Indicate if another object is 'equal' to this object.
     *
     * @param obj Reference object with which to compare to.
     * @return true if they are equal.
     *         false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeadlineCommand) {
            DeadlineCommand otherDeadlineCommand = (DeadlineCommand) obj;
            return otherDeadlineCommand.action.equals(this.action);
        }
        return false;
    }
}
