import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * A constructor for a TaskList which contains Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Given a string, creates a To-do from that string and adds it to the list of task
     * @param taskTitle a String of the title of the To-do to be added.
     * @return the newly created Todo.
     */
    public Task addTodo(String taskTitle) {
        Todo task = new Todo(taskTitle);
        this.tasks.add(task);
        return task;
    }

    /**
     * Given a string, creates a Deadline from that string and adds it to the list of task
     * @param taskTitle a String of the title of the Deadline to be added.
     * @return the newly created Deadline.
     */
    public Task addDeadline(String taskTitle) {
        Deadline task = new Deadline(taskTitle);
        this.tasks.add(task);
        return task;
    }

    /**
     * Given a string, creates a Deadline from that string and adds it to the list of task
     * @param taskTitle a String of the title of the Deadline to be added.
     * @return the newly created Deadline.
     */
    public Task addEvent(String taskTitle) {
        Event task = new Event(taskTitle);
        this.tasks.add(task);
        return task;
    }

    /**
     * Given the index number of a task, marks that task as completed
     *
     * @param taskNumber an int representing the index of the task
     * @return the String representation of the completed task
     */
    public String completeTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        // Assumes that the task exists.
        Task task = this.tasks.get(taskIndex);
        task.completeTask();
        return task.toString();
    }

    /**
     * Tells the user how many tasks there are in the list.
     *
     * @return A string that contains the number of tasks in the list.
     */
    public String countTasks() {
        return String.format("\nNow you have %d tasks in the list.", this.tasks.size());
    }

    /**
     * Deletes a task when given its index number
     *
     * @param taskNumber an int representing the index of the task
     * @return the String representation of the deleted task
     */
    public String deleteTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        // Assumes that the task exists.
        Task task = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        return task.toString();
    }

    /**
     * Returns a string representing the list of tasks.
     *
     * @return A string representing the task list
     */
    @Override
    public String toString() {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < this.tasks.size(); i++) {
            int index = i + 1;
            output += "\n" + index + "." + this.tasks.get(i).toString();
        }
        return output;
    }
}
