package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {

    /**
     * Constructor for class DoneCommand
     *
     * @param userInput  user's input
     */
    public DoneCommand(String userInput) {
        super(userInput);
    }

    /**
     * Marks a task as done in the list of tasks
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
            throw new DukeException("OOPS!!! Please enter \"done\" followed the number corresponding to "
                    + "the task you want to mark as completed");
        } else {
            int taskDone = Integer.parseInt(parsedUserInput[1]) - 1;
            tasks.getTasks().get(taskDone).markAsCompleted();
            storage.updateLocalStorage(tasks.getTasks());
            ui.reply("Nice! I've marked this duke.task as done: \n" + printDoneTask(taskDone, tasks));
        }
    }

    /**
     * Generates a string of the done task
     *
     * @param pos position of done task in the ArrayList
     * @param tasks the ArrayList
     * @return string of the done task
     */
    public String printDoneTask(int pos, TaskList tasks) {
        Task currentTask = tasks.getTasks().get(pos);
        return (pos + 1) + "." + currentTask.toString();
    }
}
