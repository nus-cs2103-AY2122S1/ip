package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * Marks a task as done in the task list.
 */
public class DoneCommand extends Command {
    /**
     * The command word that identifies a DoneCommand instance.
     */
    public static final String COMMAND_WORD = "done";

    /**
     * Guide on how to use this command word.
     */
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " <number> - mark task <number> as done\n" + "   Example: " + COMMAND_WORD + " 1";

    private String userCommand;

    /**
     * Instantiates DoneCommand object.
     *
     * @param userCommand full user input.
     */
    public DoneCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (this.userCommand.strip().length() < 5) {
                throw new IllegalArgumentException("Please enter a numeric character to mark your task as done!");
            }

            int index = Integer.parseInt(this.userCommand.substring(5).strip()) - 1;
            tasks.markTaskDone(index);
            storage.save(tasks.getItems());

            ui.printHorizontalLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.getTask(index));
            ui.printHorizontalLine();

        } catch (NumberFormatException e) {
            // error encountered when command followed by done is not Number e.g. done one
            ui.printError("Please enter a numeric character to mark your task as done!");
        } catch (IOException | IllegalArgumentException e) {
            ui.printError(e.getMessage());

        } catch (IndexOutOfBoundsException e) {
            // error when try to mark an invalid task as done / delete task

            ui.printHorizontalLine();

            if (tasks.getSize() > 0) {
                System.out.printf("That task does not exist!\nPlease enter a number from 1 to %d.\n", tasks.getSize());
            } else {
                System.out.println("You have no tasks in your list to mark as done or delete.");
            }

            ui.printHorizontalLine();
        }
    }
}
