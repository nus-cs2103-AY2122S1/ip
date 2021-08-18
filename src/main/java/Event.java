public class Event extends Task{
    private String taskName;
    private String at;
    private String type = "E";

    public Event(String taskName) {
        String[] TaskBySplit = taskName.split("/", 2);
        String[] removeAt = TaskBySplit[1].split(" ", 2);
        this.taskName = TaskBySplit[0];
        this.at = removeAt[1];
    }

    public String showTask() {
        return taskName + "(at: " + this.at + ")";
    }

    public String showType() {
        return "[" + type + "]";
    }
}
