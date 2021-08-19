public class TaskList {

    private Task[] tasks;
    private int taskCount = 0;

    public TaskList(int size) {
        this.tasks = new Task[size];
        this.taskCount = 0;
    }

    public void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        Session.output("I Understand. I Have Added:\n   " + task.toString() + "\nYou Have " + taskCount + " Tasks.");
    }

    public void listTasks() {
        String taskListString = "Here Are The Tasks In Your List:\n";
        for(int i = 0; i < taskCount; i++) {
            taskListString += (i+1) + "." + tasks[i].toString() + "\n";
        }
        taskListString += "I Wish You Luck With Your Tasks And Doings.";
        Session.output(taskListString);
    }

    public void markAsDone(int index) {
        String doneString = "Good Work. I Mark This Task As Done:\n";
        Task doneTask = tasks[index - 1];
        doneTask.markAsDone();
        doneString += "   " + doneTask.toString();
        Session.output(doneString);
    }

}
