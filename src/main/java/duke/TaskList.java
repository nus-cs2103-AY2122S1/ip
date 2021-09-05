package duke;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a list of Task
 */
public class TaskList {

    protected List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to task list.
     *
     * @param task a task to be added to list
     */
    public void add(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        save();
    }

    /**
     * Marks a task as done.
     *
     * @param number the task number in the task list
     */
    public void markDone(int number) {
        Task task = taskList.get(number - 1);
        task.markDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
        save();
    }

    /**
     * Removes task from task list.
     *
     * @param number the task number in the task list
     */
    public void remove(int number) {
        Task task = taskList.get(number - 1);
        taskList.remove(number - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        save();
    }

    /**
     * Prints all the task in task list.
     */
    public void list() {
        int counter = 1;
        for (Task task: taskList) {
            System.out.println(counter + "." + task);
            counter++;
        }
        if (counter == 1) {
            System.out.println("You have no more task!");
        }
    }

    /**
     * Prints all the task that contains the keyword.
     *
     * @param keyword a string to find task
     */
    public void find(String keyword) {
        int counter = 1;
        System.out.println("The following tasks contain keyword: " + keyword);
        for (Task task: taskList) {
            if (task.getDescription().contains(keyword)) {
                System.out.println(counter + "." + task);
            }
            counter++;
        }
    }

    /**
     * Removes all task in task list.
     */
    public void clear() {
        taskList = new ArrayList<>();
        save();
    }

    /**
     * Returns if task list is empty.
     *
     * @return if task list is empty
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Returns task list saved from previous session.
     *
     * @return a TaskList object
     */
    public static TaskList load() throws FileNotFoundException {
        return new TaskList(Storage.retrieveTaskList());
    }

    /**
     * Stores task list on local computer as a text file.
     */
    public void save() {
        try {
            Storage.saveTaskList(taskList);
        } catch (Exception exception) {
            System.out.println("Saving failed: " + exception);
        }
    }
}
