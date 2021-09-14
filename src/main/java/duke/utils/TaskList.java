package duke.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import duke.exceptions.DuplicateTaskException;
import duke.exceptions.InvalidTaskNumberException;
import duke.tasks.Task;

/** Class containing all tasks currently stored in Duke */
public class TaskList {
    private List<Task> tasks;

    /**
     * TaskList constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * TaskList constructor.
     *
     * @param tasks list of tasks to be added.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    private Task getTask(int i) throws InvalidTaskNumberException {
        if (i == 0 || i > tasks.size()) {
            throw new InvalidTaskNumberException(tasks.size());
        } else {
            Task output = tasks.get(i - 1);
            return output;
        }
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return size of the taskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task Task to be added to taskList.
     */
    public void addTask(Task task) throws DuplicateTaskException {
        assert (task != null) : "TaskList should not contain any null objects";
        if (!tasks.contains(task)) {
            tasks.add(task);
        } else {
            throw new DuplicateTaskException(task);
        }
    }

    /**
     * Deletes the ith task.
     * Counting from 1.
     *
     * @param i Index of task to delete.
     * @return String[] Strings telling the user what this method has done.
     * @throws InvalidTaskNumberException if i == 0 or i >= number of tasks in list.
     */
    public String[] deleteTask(int i) throws InvalidTaskNumberException {
        Task task = getTask(i);
        boolean removed = tasks.remove(task);
        return new String[] {
            "I have deleted this task:",
            "    " + task.toString(),
            String.format("Now you have %d tasks in the list.", tasks.size())
        };
    }

    /**
     * Marks the ith task as done.
     * Counting from 1.
     *
     * @param i Index of task to mark as done.
     * @return String[] Strings telling the user what this method has done.
     * @throws InvalidTaskNumberException if i == 0 or i >= number of tasks in list.
     */
    public String[] markDone(int i) throws InvalidTaskNumberException {
        Task task = getTask(i);
        boolean isMarked = task.markDone();
        if (isMarked) {
            return new String[] {"Nice! I've marked this task as done:", "    " + task.toString()};
        } else {
            return new String[] {"This was completed previously:", "    " + task.toString()};
        }
    }

    /**
     * Returns String representation of taskList, which will be used to save to disk.
     *
     * @return String representation of taskList, which will be used to save to disk.
     */
    public String getData() {
        StringBuilder data = new StringBuilder();
        for (Task task : tasks) {
            data.append(task.toDataString()).append("\n");
        }

        if (data.length() > 0) {
            return data.toString().substring(0, data.length() - 1);
        } else {
            return "";
        }
    }

    /**
     * Prints the current contents of the list in a user-friendly manner.
     *
     * @param cliUi Ui object.
     */
    public String find(HashSet<String> keywords, CliUi cliUi) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
            if (task.containsKeywords(keywords)) {
                try {
                    filteredTasks.addTask(task);
                } catch (DuplicateTaskException e) {
                    // will never occur since the original taskList is correct
                }
            }
        }
        if (filteredTasks.getSize() == 0) {
            cliUi.printOut("No matching tasks found!");
            return "No matching tasks found!";
        } else {
            return String.join("\n", filteredTasks.showList(cliUi));
        }
    }

    /**
     * Returns array containing lines to be printed.
     *
     * @param cliUi Ui object.
     * @return String array containing lines to be printed.
     */
    public String[] showList(CliUi cliUi) {
        cliUi.printOut(toStrings());
        return toStrings();
    }

    private String[] toStrings() {
        if (tasks.size() == 0) {
            return new String[] {"No tasks added yet!"};
        } else {
            String[] messages = new String[tasks.size()];
            int i = 0;
            for (Task task : tasks) {
                messages[i] = String.format("%d. %s", ++i, task.toString());
            }
            return messages;
        }
    }

    /**
     * Returns String representation of the taskList.
     *
     * @return String representation of the taskList.
     */
    @Override
    public String toString() {
        return String.join("\n", toStrings());
    }
}
