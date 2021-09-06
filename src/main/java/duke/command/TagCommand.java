package duke.command;

import duke.exception.InvalidInputException;
import duke.exception.SaveFileException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.util.Reply;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command class encapsulating the logic that occurs when the user issues a 'tag' command.
 */
public class TagCommand extends Command {
    /** String input of the action from the user */
    private final String action;

    /**
     * Constructor of the TagCommand class
     *
     * @param action String input of the action from the user.
     */
    public TagCommand(String action) {
        super(false);
        this.action = action;
    }

    /**
     * Finds the task and add the tag to it
     *
     * @param tasks List of existing tasks
     * @param storage Storage class handling the persistence of the tasks
     * @return CommandResult of the encapsulating the effects of the command after it completes
     * @throws InvalidInputException Indicates that a invalid input is given
     * @throws TaskNotFoundException Indicates that the task suggested is not in the task list
     * @throws SaveFileException Indicates a save file error
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage)
            throws InvalidInputException, TaskNotFoundException, SaveFileException {
        String[] actionSplit = this.action.split(" ");
        try {
            if (actionSplit.length <= 1) {
                throw new InvalidInputException("The 'tag' command requires an integer and word.");
            }
            if (actionSplit.length > 2) {
                throw new InvalidInputException("A tag can only be a word");
            }
            int taskNumber = Integer.parseInt(actionSplit[0]);

            if (taskNumber <= tasks.size() && taskNumber > 0) {
                Task currTask = tasks.get(taskNumber - 1);
                currTask.setTag(actionSplit[1]);
                storage.save(tasks);
                return new CommandResult(Reply.showTag(currTask), true, super.isExit());
            } else {
                throw new TaskNotFoundException("The task chosen does not exist. Use 'list' to see all your tasks.");
            }

        } catch (NumberFormatException e) {
            throw new InvalidInputException("The 'tag' command requires an integer as the second parameter");
        }
    }
}
