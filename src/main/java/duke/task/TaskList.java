package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for a TaskList.
     *
     * @param taskList list of Tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t \t " + task);
        System.out.println(taskSummary());
    }

    /**
     * Generate a summary of the TaskList.
     *
     * @return String summary of TaskList.
     */
    public String taskSummary() {
        int numTasks = this.taskList.size();
        String size = numTasks == 0 ? "no" : String.valueOf(numTasks);
        String maybePlural = numTasks == 1 ? " task " : " tasks ";
        return "\t You have " + size + maybePlural + "in the list.\n";
    }

    /**
     * Remove a task from the TaskList.
     *
     * @param task Task to remove.
     */
    public void deleteTask(Task task) {
        this.taskList.remove(task);
        System.out.println("\t Got it. I've removed this task:");
        System.out.println("\t \t " + task);
        System.out.println(taskSummary());
    }

    /**
     * Generates a formatted string for storing TaskList.
     *
     * @return String for storing TaskList.
     */
    public List<String> formatStorage() {
        return taskList.stream().map(Task::storageString).collect(Collectors.toList());
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return true if TaskList is empty, else false.
     */
    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    @Override
    public String toString() {
        String toPrint = "";

        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            toPrint += ("\t " + index + ". " + this.taskList.get(i) + "\n");
        }

        return toPrint;
    }

    /**
     * Clear the TaskList.
     */
    public void clearTasks() {
        taskList.clear();
    }

    /**
     * Get a Task from the TaskList.
     *
     * @param taskNum index of Task in TaskList.
     * @return Task that is retrieved.
     */
    public Task getTask(int taskNum) {
        if (taskNum > this.taskList.size() || taskNum < 1) {
            return null;
        } else {
            return this.taskList.get(taskNum - 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskList taskList1 = (TaskList) o;
        return taskList.equals(taskList1.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList);
    }
}
