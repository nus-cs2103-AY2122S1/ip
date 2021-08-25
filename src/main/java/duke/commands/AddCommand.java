package duke.commands;

import duke.exceptions.DukeException;
import duke.functionality.Ui;
import duke.functionality.Storage;
import duke.tasks.*;

/**
 * Represents the command when the user wants to add a new task.
 */
public class AddCommand extends Command {
    private String taskType;
    private String taskName;
    private String datetime;

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
     */
    public void execute(Storage storage, Ui ui) {
        if (this.taskType.equals(Todo.taskTag())) {
            int taskListLen = storage.taskListLen();
            if (taskListLen < TaskList.MAX_TASKS) {
                Todo newTodo = new Todo(this.taskName);
                storage.addTask(newTodo);
                taskListLen += 1;
                storage.saveToFile();
                ui.taskAddedMessage(newTodo, taskListLen);
            } else {
                ui.maxTaskReachedMessage();
            }
        } else if (this.taskType.equals(Deadline.taskTag())) {
            int taskListLen = storage.taskListLen();
            if (taskListLen < TaskList.MAX_TASKS) {
                Deadline newDeadline = new Deadline(taskName, this.datetime);
                storage.addTask(newDeadline);
                taskListLen += 1;
                storage.saveToFile();
                ui.taskAddedMessage(newDeadline, taskListLen);
            } else {
                ui.maxTaskReachedMessage();
            }
        } else if (this.taskType.equals(Event.taskTag())) {
            int taskListLen = storage.taskListLen();
            if (taskListLen < TaskList.MAX_TASKS) {
                Event newEvent = new Event(this.taskName, this.datetime);
                storage.addTask(newEvent);
                taskListLen += 1;
                storage.saveToFile();
                ui.taskAddedMessage(newEvent, taskListLen);
            } else {
                ui.maxTaskReachedMessage();
            }
        }
    }

    public boolean isExit() {
        return false;
    }

}
