package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.task.Deadline;
import duke.task.Task;
import duke.util.CommandModifier;
import duke.util.ExceptionChecker;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class that handles deadline-addition command.
 */
public class DeadlineCommand extends Command {

    private static final String commandType = "deadline";
    private static final String delimiter = " /by ";

    private String[] tags;
    private String description;
    private LocalDate date;

    /**
     * Constructs an TodoCommand instance that handles the logic of deadline-addition.
     *
     * @param description The deadline description.
     * @throws EmptyDescriptionException The exception being thrown when the deadline has empty description.
     */
    public DeadlineCommand(String description) throws DukeException {
        ExceptionChecker.checkEmptyDescription(commandType, description);
        ExceptionChecker.checkMissingArgument(commandType, description, delimiter);
        ExceptionChecker.checkIncompleteDescription(commandType, description, delimiter);
        ExceptionChecker.checkInvalidDateFormat(description, delimiter);
        ExceptionChecker.checkDateParsing(description, delimiter);
        this.tags = CommandModifier.getTags(description);
        this.description = CommandModifier.removeTagsFrom(tags, description);
        this.date = CommandModifier.toLocalDate(description, delimiter);
    }

    // Returns a response telling the user that the task has been successfully added and stored.
    private String createResponse(TaskList tasks, Task task) {
        String prefix = "HOHOHO. I've added this deadline:\n ";
        int taskNum = tasks.getTaskNum();
        String summary = "\nNow you have " + taskNum + " tasks in the list.";

        return String.format("%s%s", prefix + task, summary);
    }

    // Returns a deadline instance.
    private Deadline createDeadline() {
        return new Deadline(tags, description, date);
    }

    /**
     * Returns the response after executing the command based on different command types.
     *
     * @param tasks   The list that stores all the tasks to be added/deleted.
     * @param ui      The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException The possible exceptions thrown when the program runs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Deadline deadline = createDeadline();
            tasks.add(deadline);
            storage.save(tasks);

            return createResponse(tasks, deadline);
        } catch (DateTimeParseException e) {
            return new Ui().showError(e.getMessage());
        }
    }

    /**
     * Returns a boolean value of whether it is a command that exit the program.
     *
     * @return The boolean value of whether it is a command that exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
