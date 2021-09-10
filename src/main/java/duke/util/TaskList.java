package duke.util;

import duke.task.Task;
import duke.exceptions.DukeIndexException;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class which encapsulates all tasks, as well as handle information and
 * operations relating to the tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor method of TaskList.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to list.
     *
     * @param task Task to be added.
     */
    public ArrayList<String> addTask(Task task) {
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add("Got it. I've added this task:");
        outputs.add(task.toString());
        tasks.add(task);
        if (tasks.size() == 1) {
            outputs.add("Now you have 1 task in the list.");
        } else {
            outputs.add(String.format("Now you have %d tasks in the list.", 
                    tasks.size()));
        }
        return outputs;
    }

    /**
     * Lists the tasks in the list of tasks.
     */
    public ArrayList<String> list() {
        ArrayList<String> outputs = new ArrayList<>();
        if (tasks.isEmpty()) {
            outputs.add("You have no task for now. Want to add a new task?");
            return outputs;
        }

        outputs.add("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            outputs.add(String.format("%d. %s",
                    i + 1, tasks.get(i)));
        }
        return outputs;
    }

    /**
     * Sets a task as done.
     *
     * @param index Index of the task to be set as done.
     * @throws DukeIndexException If index given by user is greater than size of list or non-positive.             
     */
    public ArrayList<String> setAsDone(int index) {
        ArrayList<String> outputs = new ArrayList<>();
        try {
            if (index > tasks.size()) {
                throw new DukeIndexException("The input task number is too big.");
            }
            if (index < 1) {
                throw new DukeIndexException("The input task number is non-positive.");
            }
            tasks.get(index - 1).maskAsDone();

            outputs.add("Nice! I've marked this task as done:");
            outputs.add(tasks.get(index - 1).toString());
        } catch (DukeIndexException e) {
            outputs.add(e.getMessage());
        }
        return outputs;
    }

    /**
     * Deletes a task from list of tasks.
     *
     * @param index Index of the task to be deleted.
     * @throws DukeIndexException If index given by user is greater than size of list.
     */
    public ArrayList<String> deleteTask(int index) throws DukeIndexException {
        ArrayList<String> outputs = new ArrayList<>();
        try {
            if (index > tasks.size()) {
                throw new DukeIndexException("The input task number is too big.");
            }
            Task removedTask = tasks.remove(index - 1);

            outputs.add("Noted. I've removed this task:");
            outputs.add("  " + removedTask);
            outputs.add(String.format("Now you have %d tasks in the list.", tasks.size()));
        } catch (DukeIndexException e) {
            outputs.add(e.getMessage());
        }
        return outputs;
    }

    /**
     * Finds a task matching the given keyword.
     *
     * @param keyword Keyword given.
     */
    public ArrayList<String> showFind(String keyword) {
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getDescription().contains(keyword)) {
                outputs.add(tasks.get(i).toString());
            }
        }
        return outputs;
    }
}



