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
    /**
     * The command word that identifies a DeleteCommand instance.
     */
    public static final String COMMAND_WORD = "delete";

    /**
     * Guide on how to use the command word.
     */
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " <number> - delete the specified task <number>\n" + "    📍 Example: " + COMMAND_WORD + " 1";

    private String userCommand;

    /**
     * Instantiates DeleteCommand object.
     *
     * @param userCommand Full user input.
     */
    public DeleteCommand(String userCommand) {
        super();
        this.userCommand = userCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (this.userCommand.length() < 7) {
                throw new IllegalArgumentException("Please enter a numeric character to delete the specified task!");
            } else {
                int index = Integer.parseInt(this.userCommand.substring(7)) - 1;
                Task taskToDelete = tasks.getTask(index);

                tasks.deleteTask(index);
                storage.save(tasks.getItems());

                // ui.printHorizontalLine();
                // System.out.println("Noted. I've removed this task:");
                // System.out.println(taskToDelete);
                // ui.printHorizontalLine();
                return ui.printTaskDeleted(taskToDelete);

            }


        } catch (NumberFormatException e) {
            // error encountered when command followed by done is not Number e.g. done one
            return ui.printError("Please enter a numeric character to delete the specified task!");

        } catch (IOException | IllegalArgumentException e) {
            return ui.printError(e.getMessage());

        } catch (IndexOutOfBoundsException e) {
            // error when try to mark an invalid task as done / delete task
            // ui.printHorizontalLine();

            if (tasks.getSize() > 0) {
                // System.out.printf("That task does not exist!\nPlease enter a number from 1 to %d.\n", tasks.getSize
                // ());
                return "That task does not exist!\nPlease enter a number from 1 to " + tasks.getSize();
            } else {
                System.out.println("You have no tasks in your list to mark as done or delete.");
                return "You have no tasks in your list to mark as done or delete.";
            }

            // ui.printHorizontalLine();
        }
    }
}
