public class TaskList {

    private Task[] tasks;
    private int taskCount = 0;

    public TaskList(int size) {
        this.tasks = new Task[size];
        this.taskCount = 0;
    }

    public void addTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;
        Session.output("added: " + description);
    }

    public void listTasks() {
        String taskListString = "";
        for(int i = 0; i < taskCount; i++) {
            taskListString += (i+1) + ". " + tasks[i].toString() + (i == taskCount-1 ? "" : "\n");
        }
        Session.output(taskListString);
    }

}
