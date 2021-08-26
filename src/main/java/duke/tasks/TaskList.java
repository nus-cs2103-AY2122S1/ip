package duke.tasks;

import duke.exceptions.InvalidTaskCreationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The {@code TaskList} contains and has methods for interacting with {@code Task}s.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes an instance of {@code TaskList} with an {@code ArrayList} containing {@code Task}s.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns an {@code ArrayList} of {@code Task}s in this {@code TaskList},
     *
     * @return {@code ArrayList} containing all tasks in this {@code TaskList}.
     */
    public ArrayList<Task> tasks() {
        return this.tasks;
    }

    /**
     * Returns the number of {@code Task}s in this {@code TaskList},
     *
     * @return {@code int} - the number of tasks in this {@code TaskList}.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a single {@code Task} to this {@code TaskList}.
     *
     * @param t {@code Task} to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Adds a single {@code Task} to this {@code TaskList},
     * then forms a String with information about the {@code TaskList}.
     *
     * @param taskType {@code String}. One of: 'todo', 'event', 'deadline'.
     * @param t {@code Task} to be added.
     * @return {@code String} containing information about the {@code TaskList} after the operation.
     */
    public String addTaskAndAnnounce(String taskType, Task t) {
        String res = "";
        tasks.add(t);
        res += "I've added your " + taskType.toLowerCase() + ":\n\t\t" + t;
        res += "\n\t " + tasks.size() + " tasks in total.";
        return res;
    }

    /**
     * Marks a given {@code listOfTasks} as done,
     * then forms a String with information about the {@code TaskList}.
     *
     * @param listOfTasks {@code ArrayList<Integer>} representing the index of {@code Task}s to be marked as done.
     * @return {@code String} containing information about the {@code TaskList} after the operation.
     */
    public String markAsDoneAndAnnounce(ArrayList<Integer> listOfTasks) {
        StringBuilder res = new StringBuilder("Nice! I've marked these tasks as done: \n\t\t");

        for (int i = 0; i < listOfTasks.size(); i++) {
            // have to decrement by one since duke.tasks ArrayList is 0-indexed,
            // but the user-provided arguments uses a 1-indexed list
            int index = listOfTasks.get(i) - 1;

            tasks.get(index).markAsDone();
            res.append(tasks.get(index));
            if (i != listOfTasks.size() - 1) {
                // do not append newline to the last item
                res.append("\n\t\t");
            }
        }
        return res.toString();
    }

    /**
     * Deletes a given {@code listOfTasks},
     * then forms a String with information about the {@code TaskList}.
     *
     * @param listOfTasks {@code ArrayList<Integer>} representing the index of {@code Task}s to be deleted.
     * @return {@code String} containing information about the {@code TaskList} after the operation.
     */
    public String deleteTasksAndAnnounce(ArrayList<Integer> listOfTasks) {
        StringBuilder res = new StringBuilder("I've removed these tasks: \n\t\t");

        // reverse-sort the tasks and remove duplicates
        Set<Integer> s = new LinkedHashSet<>(listOfTasks);
        listOfTasks.clear();
        listOfTasks.addAll(s);
        Collections.sort(listOfTasks);
        Collections.reverse(listOfTasks);

        for (int i = 0; i < listOfTasks.size(); i++) {
            // have to decrement by one since tasks ArrayList is 0-indexed,
            // but the user-provided arguments uses a 1-indexed list
            int index = listOfTasks.get(i) - 1;

            res.append(tasks.get(index));
            tasks.remove(index);

            if (i != listOfTasks.size() - 1) {
                // do not append newline to the last item
                res.append("\n\t\t");
            }
        }
        res.append("\n\t ").append(tasks.size()).append(" tasks remain.");
        return res.toString();
    }

    /**
     * Returns an indented, newlined, 1-indexed {@code String} of the
     * {@code Task}s contained in this {@code TaskList}.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Here are your tasks:\n\t ");
        for (int i = 0; i < tasks.size(); i++) {
            res.append(i+1).append(". ").append(tasks.get(i));
            if (i != tasks.size() - 1) {
                // do not append a newline to the last item
                res.append("\n\t ");
            }
        }
        return res.toString();
    }
}
