package katheryne.command;

import katheryne.KatheryneException;
import katheryne.Storage;
import katheryne.TaskList;
import katheryne.Ui;
import katheryne.WrongIndexException;

/**
 * Command to mark a task as done
 */
public class DoneCommand extends Command {
    /**
     * The constant name to refer to this command by
     */
    public static final String COMMAND = "DONE";
    private final int index;

    /**
     * Creates a new done command that will mark a given item of the list as done. Keep in mind
     * that the user given index will be one more than the actual desired index.
     *
     * @param processedRemainingText
     * @throws KatheryneException if number format is wrong
     */
    DoneCommand(String[] processedRemainingText) throws KatheryneException {
        // Will not throw a NullPointerException as it is checked for nulls in the factory method
        try {
            this.index = Integer.parseInt(processedRemainingText[0]) - 1;
        } catch (NumberFormatException e) {
            throw new WrongIndexException();
        }
    }

    /**
     * Marks a task as complete. Prints a confirmation message if successful
     * 
     * @param taskList A container for tasks which contains Katheryne's tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     * @throws KatheryneException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KatheryneException {
        boolean isDone = taskList.doTask(index);
        if (isDone) {
            ui.say("Task done: " + taskList.getTask(index));
        } else {
            throw new WrongIndexException();
        }
    }
}
