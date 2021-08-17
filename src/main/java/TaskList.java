public class TaskList {
    private Task[] tasks;
    private int counter;

    /**
     * A constructor for a TaskList which contains Tasks.
     */
    public TaskList() {
        this.counter = 0;
        this.tasks = new Task[100];
    }

    /**
     * Given a
     * @param taskTitle
     */
    public void addTask(String taskTitle) {
        Task task = new Task(taskTitle);
        this.tasks[counter] = task;
        counter ++;
    }
}
