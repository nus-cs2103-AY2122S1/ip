package duke.util;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;


/**
 * TaskList class which encapsulates all tasks, as well as handle information and
 * operations relating to the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor method of TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to taskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Formats the added tasks.
     *
     * @param task Task to be added.
     */
    public String formatAddedTask(Task task) { 
        String output = "Got it. I've added this task: \n";
        output += task.toString() + "\n";
        if (tasks.size() == 1) {
            output += "Now you have 1 task in the list.\n";
        } else {
            output += String.format("Now you have %d tasks in the list.\n",
                    tasks.size());
        }
        return output;
    }

    /**
     * Lists the tasks in the list of tasks.
     */
    public String list() {
        if (tasks.isEmpty()) {
            return "You have no task for now. Want to add a new task? \n";
        } else {
            String output = "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                output += String.format("%d. %s",
                        i + 1, tasks.get(i)) + "\n";
            }
            return output;
        }
    }

    /**
     * Sets a task as done.
     *
     * @param index Index of the task to be set as done.
     */
    public String setAsDone(int index) {
        assert index <= tasks.size();
        String output = "Nice! I've marked this task as done: \n";
        tasks.get(index - 1).markAsDone();
        output += tasks.get(index - 1).toString() + "\n";
        return output;
    }


    /**
     * Deletes a task from list of tasks.
     *
     * @param index Index of the task to be deleted. 
     */
    public String deleteTask(int index) {
        assert index <= tasks.size();
        String output = "Noted. I've removed this task:\n";
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        output += task.toString() + "\n";
        if (tasks.size() == 1) {
            output += "Now you have 1 task in the list.\n";
        } else {
            output += "Now you have " + tasks.size() + " tasks in the list.\n";
        }
        return output;
    }

    /**
     * Returns the number of task in the list.
     *
     * @return Integer indicating number of tasks in list.
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * Finds a task matching the given keyword.
     *
     * @param keyword Keyword given.
     */
    public String showFind(String keyword) {
        ArrayList<Task> outputs = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                cnt++;
            }
        }
        if (cnt == 0) {
            return "You have no matching tasks in your list to this keyword. :(\n";
        } else {
            String output;
            if (cnt == 1) {
                output = "You have 1 matching task in your list. :)\n"
                        + "It is:\n";
            } else {
                output = "You have " + cnt + " matching tasks in your list. :)\n"
                        + "They are:\n";
            }

            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(keyword)) {
                    outputs.add(tasks.get(i));
                    output += i + 1 + ". " + tasks.get(i).toString() + "\n";
                }
            }
            return output;
        }
    }
}