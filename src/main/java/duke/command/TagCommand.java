package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class TagCommand extends Command {
    private int taskId;
    private String tag;
    /**
     * Class constructor
     *
     * @param taskId index of the task to be tagged
     * @param tag the tag use to tag the selected task
     */
    public TagCommand(int taskId, String tag) {
        this.taskId = taskId;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        Task taskToBeTagged = tasks.getTask(taskId);
        taskToBeTagged.tagTask(tag);
        
    }
}
