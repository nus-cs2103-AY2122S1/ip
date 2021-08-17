public class TaskList {
    private Task[] tasks;
    private static final int MAX_TASKS = 200;
    private int counter;

    /**
     * A constructor for a TaskList which contains Tasks.
     */
    public TaskList() {
        this.counter = 0;
        this.tasks = new Task[MAX_TASKS];
    }

    /**
     * Given a string, creates a Task from that string and adds it to the list of task
     * @param taskTitle the title of the task to be added.
     */
    public void addTask(String taskTitle) {
        Task task = new Task(taskTitle);
        this.tasks[counter] = task;
        counter ++;
    }

    /**
     * Returns a string representing the list of tasks.
     *
     * @return A string representing the task list
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < counter; i++) {
            int index = i + 1;
            output += index + ". " + this.tasks[i].toString() + "\n";
        }
        return output;
    }
}
