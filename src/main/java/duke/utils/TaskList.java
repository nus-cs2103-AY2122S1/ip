package duke.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import duke.exceptions.InvalidTaskNumberException;
import duke.tasks.Task;

/** Class containing all tasks currently stored in Duke */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    private Task getTask(int i) throws InvalidTaskNumberException {
        if (i == 0 || i > tasks.size()) {
            throw new InvalidTaskNumberException(tasks.size());
        } else {
            return tasks.get(i - 1);
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
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
        return new String[] {"I have deleted this task:", "    " + task.toString()};
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
        boolean marked = task.markDone();
        if (marked) {
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
     * @param ui Ui object.
     */
    public void find(HashSet<String> keywords, Ui ui) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
            if (task.containsKeywords(keywords)) {
                filteredTasks.addTask(task);
            }
        }
        if (filteredTasks.getSize() == 0) {
            ui.printOut("No matching tasks found!");
        } else {
            filteredTasks.showList(ui);
        }
    }

    public void showList(Ui ui) {
        ui.printOut(toStrings());
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

    @Override
    public String toString() {
        return String.join("\n", toStrings());
    }
}
