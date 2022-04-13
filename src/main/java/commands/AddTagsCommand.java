package commands;

import duke.Storage;
import duke.TaskList;
import duke.Tag;
import duke.Ui;
import tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This command adds tags to a given task
 */
public class AddTagsCommand implements Command {
    private final int index;
    private final String[] tagsToAdd;

    public AddTagsCommand(int index, String[] tags) {
        this.index = index;
        this.tagsToAdd = tags;
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            Task task = taskList.getTask(this.index);
            task.addTags(this.tagsToAdd);
            storage.writeTasksToFile(taskList, storage.getTaskFile());
            return ui.getSuccessfulTagResponse(task, this.index, tagsToAdd);
        } catch (IndexOutOfBoundsException e) {
            if (taskList.numberOfTasks() > 0) {
                return "Invalid index given, enter a number from 1 to " + taskList.numberOfTasks();
            } else if (taskList.numberOfTasks() == 0) {
                return "You cannot add tags as you have no tasks!";
            }
        } catch (IOException e) {
            return ui.getFileWriteFailResponse(storage.getTaskFile());
        }
        return "";
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
