package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    /**
     * A constructor for a duke.TaskList which contains Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Converts a taskList to a text format that can be saved in a txt file.
     * @return a String that represents the savable data of a duke.TaskList.
     */
    public String toSaveData() {
        String data = "";
        for (Task task : this.tasks) {
            data += task.toSaveData();
        }
        return data;
    }
    /**
     * Adds an existing task to the list of tasks.
     * @param task The task to be added to the list of tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    /**
     * Given a string, creates a To-do from that string and adds it to the list of task.
     * @param taskTitle a String of the title of the To-do to be added.
     * @return the newly created duke.task.ToDo.
     */
    public String addNewTodo(String taskTitle) {
        ToDo task = new ToDo(taskTitle);
        this.tasks.add(task);
        return "Got it. I've added this task:\n\t" + task.toString() + this.countTasks();
    }
    /**
     * Given a string, creates a duke.task.Deadline from that string and adds it to the list of task.
     * @param taskTitle a String of the title of the duke.task.Deadline to be added.
     * @return the newly created duke.task.Deadline.
     */
    public String addNewDeadline(String taskTitle) {
        int delimiter = taskTitle.indexOf("/by ");
        LocalDate due = LocalDate.parse(taskTitle.substring(delimiter + 4));
        Deadline task = new Deadline(taskTitle.substring(0, delimiter), due);
        this.tasks.add(task);
        return "Got it. I've added this task:\n\t" + task.toString() + this.countTasks();
    }

    /**
     * Given a string, creates a duke.task.Deadline from that string and adds it to the list of task.
     * @param taskTitle a String of the title of the duke.task.Deadline to be added.
     * @return the newly created duke.task.Deadline.
     */
    public String addNewEvent(String taskTitle) {
        int delimiter = taskTitle.indexOf("/at ");
        LocalDate due = LocalDate.parse(taskTitle.substring(delimiter + 4));
        Event task = new Event(taskTitle.substring(0, delimiter), due);
        this.tasks.add(task);
        return "Got it. I've added this task:\n\t" + task.toString() + this.countTasks();
    }
    /**
     * Given the index number of a task, marks that task as completed.
     *
     * @param taskNumber an int representing the index of the task
     * @return the String representation of the completed task
     */
    public String completeTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        // Assumes that the task exists.
        Task task = this.tasks.get(taskIndex);
        task.completeTask();
        return "Nice! I've marked this task as done:\n\t" + task.toString();
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
     * Deletes a task when given its index number.
     *
     * @param taskNumber an int representing the index of the task
     * @return the String representation of the deleted task
     */
    public String deleteTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        // Assumes that the task exists.
        return "Noted. I've removed this task:\n\t"
                + this.tasks.remove(taskIndex).toString()
                + this.countTasks();
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
