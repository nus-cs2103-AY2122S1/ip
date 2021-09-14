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
    public String markDone(int number) {
        assert number >= 1 && number <= taskList.size();
        Task task = taskList.get(number - 1);
        task.markDone();
        save();
        String s = "Nice! I've marked this task as done: \n" + task + "\n";
        System.out.print(s);
        return s;
    }

    /**
     * Removes task from task list.
     *
     * @param number the task number in the task list
     */
    public String remove(int number) {
        assert number >= 1 && number <= taskList.size();
        Task task = taskList.get(number - 1);
        taskList.remove(number - 1);
        save();
        StringBuilder resultString = new StringBuilder();
        resultString.append("Noted. I've removed this task: \n" + task + "\n");
        resultString.append("Now you have " + taskList.size() + " tasks in the list.\n");
        System.out.print(resultString);
        return resultString.toString();
    }

    /**
     * Prints all the task in task list.
     */
    public void list() {
        System.out.print(this);
    }

    /**
     * Formats the tasks into a list form.
     *
     * @return String of all task in list form
     */
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        int counter = 1;
        for (Task task: taskList) {
            resultString.append(counter + "." + task + "\n");
            counter++;
        }
        if (counter == 1) {
            resultString.append("You have no more task!\n");
        }
        return resultString.toString();
    }

    /**
     * Prints all the task that contains the keyword.
     *
     * @param keyword a string to find task
     */
    public String find(String keyword) {
        StringBuilder resultString = new StringBuilder();
        int counter = 1;
        resultString.append("The following tasks contain keyword: " + keyword + "\n");
        for (Task task: taskList) {
            if (task.getDescription().contains(keyword)) {
                resultString.append(counter + "." + task + "\n");
            }
            counter++;
        }
        System.out.print(resultString);
        return resultString.toString();
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
     * Returns number of task in list.
     *
     * @return if task list is empty
     */
    public int size() {
        return taskList.size();
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
