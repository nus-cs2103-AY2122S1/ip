package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.AfterTask;

public class AddChainCommand extends Command {
    private final String description;
    private final String taskId;

    public AddChainCommand(String description, String taskId) {
        this.description = description;
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        int index = Integer.parseInt(this.taskId) - 1;
        taskList.getTaskList().get(index).setAfterTask(new AfterTask(this.description));
        return getReplyMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getReplyMessage() {
        return this.description + " task is queued, and will be available after you completed task index " + this.taskId;
    }
    
}
