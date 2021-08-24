package duke.main;

import java.util.ArrayList;
import java.util.Iterator;

import duke.task.Task;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds the Task into the task list.
     *
     * @param newTask the Task to be added into the task list.
     */
    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    public Task removeTask(int taskIndex) {
        return this.taskList.remove(taskIndex);
    }

    public Task markDoneInTaskList(int taskIndex) {
        Task task = this.taskList.get(taskIndex);
        task.markAsDone();
        return task;
    }

    public void printList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println(i + 1 + ". " + this.taskList.get(i));
        }
    }

    /**
     * Prints the number of tasks currently in the task list.
     */
    public void printNumberOfTasks() {
        System.out.println("\nYou now have " + this.taskList.size()
                + (this.taskList.size() == 1 ? " task " : " tasks ") + "in your list!");
    }

    public void printAllContains(String match) {
        boolean taskFound = false;
        for (int i = 0; i < this.taskList.size(); i++) {
            String description = taskList.get(i).getDescription();
            if (description.contains(match)) {
                taskFound = true;
                System.out.println("Task " + (i + 1) + ". " + this.taskList.get(i));
            }
        }

        if (!taskFound) {
            System.out.println("    -- NO MATCHING TASKS FOUND --");
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return this.taskList.iterator();
    }
}
