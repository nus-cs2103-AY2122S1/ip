package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Represents the command when the user wants to add a new task.
 */
public class AddCommand extends Command {
    private String taskType;
    private String taskName;
    private String datetime;

    /**
     * Returns a Command representing an addition of a new task.
     *
     * @param taskType The type of task to be added.
     * @param taskName The name of the task to be added.
     * @param datetime The date/time of the task to be added, if any.
     */
    public AddCommand(String taskType, String taskName, String datetime) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.datetime = datetime;
    }

    /**
     * Executes the command, with execution method depending on the type of the task being added through this command.
     *
     * @param storage The storage system of the bot involved with this command.
     * @param ui The ui of the bot involved with this command.
     * @return Message indicating whether the task has been successfully added.
     */
    public String execute(Storage storage, Ui ui) {
        int taskListLen = storage.taskListLen();
        if (taskListLen >= TaskList.MAX_TASKS) {
            return ui.maxTaskReachedMessage();
        }

        Task newTask;
        if (this.taskType.equals(Todo.taskTag())) {
            newTask = new Todo(this.taskName);
        } else if (this.taskType.equals(Deadline.taskTag())) {
            newTask = new Deadline(taskName, this.datetime);
        } else if (this.taskType.equals(Event.taskTag())) {
            newTask = new Event(this.taskName, this.datetime);
        } else {
            //Should never reach this state.
            return "Impossible task.";
        }

        storage.addTask(newTask);
        taskListLen++;
        storage.saveToFile();
        return ui.taskAddedMessage(newTask, taskListLen);

    }

}
