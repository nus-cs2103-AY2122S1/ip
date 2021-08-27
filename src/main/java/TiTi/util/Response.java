package TiTi.util;

import TiTi.task.Task;

/**
 * Represent the response required for Ui to deliver.
 * Contain a Cue of what event to preform.
 * Contain the task to be printed (if needed).
 */
public class Response {
    public enum Cue {EXIT, LIST, DONE, DELETE, TASKERROR, TODO, DEADLINE,
            EVENT, MISSINGDESCRIPTION, UNRECOGNISED};
    public Cue cue;
    public Task task;

    /**
     * Constructor for Response class.
     *
     * @param cue type of event
     * @param task task modified
     */
    public Response(Cue cue, Task task) {
        this.cue = cue;
        this.task = task;
    }
}
