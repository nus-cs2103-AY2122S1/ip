package TiTi.util;

import TiTi.task.Task;

/**
 * Represent the response required for Ui to deliver.
 * Contain a Cue of what event to preform.
 * Contain the task to be printed (if needed).
 */
public class Response {
    public enum Cue {EXIT, LIST, DONE, DELETE, TASKERROR, TODO, DEADLINE,
            EVENT, MISSINGDESCRIPTION, UNRECOGNISED, FIND};
    public Cue cue;
    public TaskList taskList;

    public Response(Cue cue) {
        this.cue = cue;
    }

    /**
     * Constructor for Response class.
     *
     * @param cue type of event
     * @param task task modified
     */
    public Response(Cue cue, Task task) {
        this.cue = cue;
        taskList = new TaskList();
        taskList.add(task);
    }

    public Response(Cue cue, TaskList taskList) {
        this.cue = cue;
        this.taskList = taskList;
    }
}
