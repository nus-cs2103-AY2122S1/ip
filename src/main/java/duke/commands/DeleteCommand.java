package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    /** The command word that identifies a DeleteCommand instance. */
    public static final String COMMAND_WORD = "delete";

    /** Length of the command word. */
    public static final int COMMAND_LENGTH = COMMAND_WORD.length();

    /** Guide on how to use the command word. */
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <number> - delete the specified task <number>\n"
                    + "    📍 Example: " + COMMAND_WORD + " 1";

    /**
     * Instantiates DeleteCommand object.
     *
     * @param userCommand Full user input.
     */
    public DeleteCommand(String userCommand) {
        super(userCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (this.userCommand.length() <= COMMAND_LENGTH) {
                throw new IllegalArgumentException(MESSAGE_INVALID_NUM);
            }

            String indexStr = this.userCommand.substring(COMMAND_LENGTH).strip();
            int index = Integer.parseInt(indexStr) - 1;
            Task taskToDelete = tasks.getTask(index);

            tasks.deleteTask(index);
            storage.save(tasks.getItems());

            return ui.printTaskDeleted(taskToDelete);
        } catch (NumberFormatException e) {
            // error encountered when command followed by done is not Number e.g. done one
            return ui.printError(MESSAGE_INVALID_NUM);
        } catch (IOException | IllegalArgumentException e) {
            return ui.printError(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            return ui.printInvalidTaskNumError(tasks);
        }
    }
}
