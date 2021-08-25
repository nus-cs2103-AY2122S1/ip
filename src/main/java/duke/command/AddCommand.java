package duke.command;

import duke.constant.TaskType;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

public class AddCommand extends Command {
    private TaskType taskType;
    private String taskDescription;
    private String date;

    public AddCommand(TaskType taskType, String taskDescription, String date) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask;
        switch (this.taskType) {
        case DEADLINE:
            newTask = new Deadline(this.taskDescription, this.date);
            break;
        case EVENT:
            newTask = new Event(this.taskDescription, this.date);
            break;
        case TODO:
            newTask = new ToDo(this.taskDescription);
            break;
        default:
            throw new DukeException("Unknown command.");
        }
        taskList.add(newTask);
        storage.appendToSave(newTask);
        ui.updateUserOnAddedTask(newTask, taskList.getNumberOfTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AddCommand) {
            AddCommand otherCommand = (AddCommand) other;
            return this.taskType == otherCommand.taskType
                    && this.taskDescription.equals(otherCommand.taskDescription)
                    && this.date.equals(otherCommand.date);
        } else {
            return false;
        }
    }
}
