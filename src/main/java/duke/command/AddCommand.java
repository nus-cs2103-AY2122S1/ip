package duke.command;

import duke.FileManager;
import duke.Tasklist;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A command which aims to add a task.
 */
public class AddCommand extends Command {
    private Task toAddTask;
    private static final String helpMessage = "To add an event, type" + "\n" + Event.syntax
            + "\nEg." + Event.inputExample
                + "\nTo add an deadline, type" + "\n" + Deadline.syntax + "\nEg." + Deadline.inputExample
                    + "\nTo add an todo, type" + "\n" + ToDo.syntax + "\nEg." + ToDo.inputExample;

    /**
     * Makes a AddCommand that adds the task that was inputted.
     *
     * @param toAddTask the task to be added.
     */
    public AddCommand(Task toAddTask) {
        this.toAddTask = toAddTask;
    }

    /**
     * Returns help message for adding.
     *
     * @return help message for adding.
     */
    public static String getHelpMessage() {
        return helpMessage;
    }

    /**
     * Adds the task to current list of task.
     *
     * @param tasks current list of task.
     * @param ui the ui to interact with the user.
     * @param fileManager the filemanger that manages the storage of duke.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, FileManager fileManager) {
        tasks.add(this.toAddTask);
        fileManager.updateTaskList(tasks, ui);
        return ui.taskAdded(this.toAddTask, tasks.getNumTasks());
    }

    /**
     * Returns if the function is a exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns string representation of AddCommand.
     * For testing purposes.
     *
     * @return string representation of AddCommand.
     */
    @Override
    public String toString() {
        return toAddTask.toString();
    }
}
