package titi.util;

import titi.task.Task;

/**
 * Represents the response required for Ui to deliver.
 * Contains a Cue of what event to preform.
 * Contains the task to be printed (if needed).
 */
public class Response {
    public enum Cue { EXIT, LIST, DONE, DELETE, TASK_ERROR, TODO, DEADLINE,
            EVENT, MISSING_DESCRIPTION, UNRECOGNISED, FIND };
    private Cue cue;
    private TaskList taskList;

    /**
     * Initialises a Deadline instance.
     *
     * @param cue type of event
     */
    public Response(Cue cue) {
        this.cue = cue;
    }


    /**
     * Initialises a Deadline instance.
     *
     * @param cue type of event
     * @param tasks tasks modified
     */
    public Response(Cue cue, Task ... tasks) {
        this.cue = cue;
        taskList = new TaskList();
        for (Task task: tasks) {
            taskList.add(task);
        }
    }

    /**
     * Initialises a Deadline instance.
     *
     * @param cue type of event
     * @param taskList tasks modified
     */
    public Response(Cue cue, TaskList taskList) {
        this.cue = cue;
        this.taskList = taskList;
    }

    public Cue getCue() {
        return cue;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
