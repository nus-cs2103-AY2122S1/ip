package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

import java.util.ArrayList;

// TODO: add javadoc
public class EditCommand extends Command {
    private final int taskIndex;
    private final String description;
    private final String date;

    public EditCommand(int taskIndex, String description, String date) {
        this.taskIndex = taskIndex;
        this.description = description;
        this.date = date;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getDate() {
        return date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // TODO: change to get single task?
        ArrayList<Task> taskArr = tasks.getAllTasks();
        Task toEditTask = taskArr.get(taskIndex);
        // TODO: can change this to desciption, date, isdone
        Task editedTask = toEditTask.getUpdatedTask(this);
        tasks.editTask(taskIndex, editedTask);
        return ui.showEditedTask(taskIndex, editedTask);
    }
}
