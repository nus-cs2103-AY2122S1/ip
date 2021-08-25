package duke.task;

public class ToDo extends Task {
    private String taskName, type = "T";

    public ToDo(String taskName) {
        this.taskName = taskName;
    }

    public String showTask() {
        return taskName;
    }

    public String showType() {
        return type;
    }

    public String showWhen() { return ""; }

    public String showTaskOnly() { return ""; }

}
