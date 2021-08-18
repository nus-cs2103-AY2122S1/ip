public class ToDo extends Task{
    private String taskName;
    private String type = "T";

    public ToDo(String taskName) {
        this.taskName = taskName;
    }

    public String showTask() {
        return taskName;
    }

    public String showType() {
        return "[" + type + "]";
    }


}
