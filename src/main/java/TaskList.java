public class TaskList {
    private StringBuilder tasks;
    private int taskLabel;

    public TaskList() {
        this.tasks = new StringBuilder();
        this.taskLabel = 1;
    }

    public void addTask(String task) {
        this.tasks.append(this.taskLabel + ". " + task + "\n");
        taskLabel++;
    }

    @Override
    public String toString() {
        if (this.taskLabel == 1) {
            return "You don't have any tasks yet, try adding something! \n";
        }
        return "Here are your tasks: \n" + this.tasks.toString();
    }
}
