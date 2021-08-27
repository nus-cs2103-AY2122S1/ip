package TiTi.util;

import TiTi.task.Task;

public class Response {
    public enum Cue {EXIT, LIST, DONE, DELETE, TASKERROR, TODO, DEADLINE,
            EVENT, MISSINGDESCRIPTION, UNRECOGNISED, FIND};
    public Cue cue;
    public TaskList taskList;

    public Response(Cue cue) {
        this.cue = cue;
    }

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
