public class Response {
    public enum Cue {EXIT, LIST, DONE, DELETE, TASKERROR, TODO, DEADLINE, EVENT, MISSINGDESCRIPTION, UNRECOGNISED};
    public Cue cue;
    public Task task;

    public Response(Cue cue, Task task) {
        this.cue = cue;
        this.task = task;
    }

}
