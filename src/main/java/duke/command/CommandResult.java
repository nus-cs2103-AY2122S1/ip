package duke.command;

import duke.tasks.TaskManager;

public class CommandResult {
    private String feedbackMessage;
    private TaskManager taskList;
    private boolean updated;

    public CommandResult(String feedbackMessage, boolean updated){
        this.updated = updated;
        this.feedbackMessage = feedbackMessage;
        taskList = null;
    }

    public CommandResult(String feedbackMessage, boolean updated, TaskManager taskList){
        this.updated = updated;
        this.feedbackMessage = feedbackMessage;
        this.taskList = taskList;
    }

    public String getFeedbackMessage(){
        return feedbackMessage;
    }

    public boolean isUpdated(){
        return updated;
    }

    public TaskManager getTaskList(){
        return taskList;
    }
}