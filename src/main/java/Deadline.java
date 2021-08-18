public class Deadline extends Task {
    private String taskName;
    private String by;
    private String type = "D";

    public Deadline(String taskName) {
        String[] TaskBySplit = taskName.split("/", 2);
        String[] removeBy = TaskBySplit[1].split(" ", 2);
        this.taskName = TaskBySplit[0];
        this.by = removeBy[1];
    }

    public String showTask() {
        return taskName + "(by: " + this.by + ")";
    }

    public String showType() {
        return "[" + type + "]";
    }
}
