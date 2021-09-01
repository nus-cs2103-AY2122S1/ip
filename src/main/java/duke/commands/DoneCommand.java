package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Marks a task as done in the task list.
 */
public class DoneCommand extends Command {
    /**
     * The command word that identifies a DoneCommand instance.
     */
    public static final String COMMAND_WORD = "done";

    /**
     * Length of the command word.
     */
    public static final int COMMAND_LENGTH = COMMAND_WORD.length();

    /**
     * Guide on how to use this command word.
     */
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " <number> - mark task <number> as done\n" + "    📍 Example: " + COMMAND_WORD + " 1";

    private String userCommand;

    /**
     * Instantiates DoneCommand object.
     *
     * @param userCommand Full user input.
     */
    public DoneCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (this.userCommand.length() <= COMMAND_LENGTH) {
                throw new IllegalArgumentException("Please enter a numeric character to mark your task as done!");
            } else {
                int index = Integer.parseInt(this.userCommand.substring(COMMAND_LENGTH).strip()) - 1;
                tasks.markTaskDone(index);
                storage.save(tasks.getItems());

                return ui.printTaskDone(tasks.getTask(index));
            }
        } catch (NumberFormatException e) {
            // error encountered when command followed by done is not Number e.g. done one
            return ui.printError("Please enter a numeric character to mark your task as done!");
        } catch (IOException | IllegalArgumentException e) {
            return ui.printError(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            // error when try to mark an invalid task as done / delete task
            if (tasks.getSize() > 0) {
                return "That task does not exist!\nPlease enter a number from 1 to " + tasks.getSize();
            } else {
                return "You have no tasks in your list to mark as done or delete.";
            }
        }
    }
}
