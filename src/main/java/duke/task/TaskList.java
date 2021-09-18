package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.Gui;

/**
 * Active list structure used to store all active tasks.
 *
 * @author mrmrinal
 */
public class TaskList {

    private List<Task> items = new ArrayList<>(100);
    private final Gui gui = new Gui();

    /**
     * Creates new TaskList instance.
     *
     * @param items Task List autofilled from the storage
     */
    public TaskList(List<Task> items) {
        this.items = items;
    }

    /**
     * Lists all the Tasks down in numerical form.
     */
    public String list() {
        if (items.size() < 1) {
            return "There are no tasks for you sir";
        } else {
            String r = "";
            for (int i = 1; items.size() >= i; i++) {
                r = r + gui.dukeResponse(i + ". "
                        + items.get(i - 1) + "\n".toString());
            }
            r = r + gui.dukeResponse("");
            return r;
        }
    }

    /**
     * Marks a specific task as done.
     *
     * @param n the position of task in the List
     */
    public void markDone(int n) {
        items.get(n).markAsDone();
    }

    /**
     * Add a new task to the List.
     *
     * @param task task that user wants to add
     */
    public void addTask(Task task) {
        items.add(task);
    }

    /**
     * Delete a specific task on the list.
     *
     * @param task Position of task that user wants to delete
     */
    public Task deleteTask(int task) {
        return items.remove(task - 1);
    }

    /**
     * Get a specific task on the list.
     *
     * @param task Position of task that user wants to obtain
     * @return task that the user queried for
     */
    public Task getTask(int task) {
        return items.get(task - 1);
    }

    /**
     * Get total number of tasks on the list.
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Update a specific task on the list.
     * @param task The task which is to be updated
     * @param number the position which has to be updated
     */
    public void updateTask(Task task, int number) {
        items.set(number - 1, task);
    }

    /**
     * Returns user the tasks that he/she searched for.
     * @param substring the user search request
     */
    public String find(String substring) {
        StringBuilder r = new StringBuilder();
        int list = 1;
        for (int i = 1; i <= items.size(); i++) {
            if (this.getTask(i).description.contains(substring)) {
                r.append(gui.dukeResponse(list + ". " + items.get(i - 1) + "\n"));
                list++;
            }
        }
        if (list == 1) {
            return gui.dukeResponse("No such tasks found");
        }
        return r + gui.dukeResponse("");
    }

}
