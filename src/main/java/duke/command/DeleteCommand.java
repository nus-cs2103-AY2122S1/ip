package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    /**
     * Constructor for class DeleteCommand
     *
     * @param userInput  user's input
     */
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    /**
     * Deletes a task from the list of tasks
     *
     * @param tasks contains the task list
     * @param ui deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException If format of user input is incorrect
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parsedUserInput = this.getUserInput().split(" ", 2);
        if (parsedUserInput.length == 1) {
            throw new DukeException("OOPS!!! Please enter \"delete\" followed the number corresponding to "
                    + "the task you want deleted");
        } else {
            try {
                int taskToDelete = Integer.parseInt(parsedUserInput[1]) - 1;
                Task deletedTask = tasks.getTasks().get(taskToDelete);
                tasks.getTasks().remove(taskToDelete);
                storage.updateLocalStorage(tasks.getTasks());
                ui.reply("Noted. I've removed this duke.task: \n" + deletedTask.toString()
                        + "\nNow you have " + tasks.getTasks().size() + " tasks in the list.");
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! Please enter \"delete\" followed the number corresponding to "
                        + "the duke.task you want deleted");
            }
        }
    }
}
