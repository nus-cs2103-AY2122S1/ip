package duke.util;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Class that handles the storing of Tasks during the app's runtime
 */
public class Tasklist {

    /** Used to store each Task */
    private ArrayList<Task> taskList;

    /** To keep track of the current number of Tasks */
    private int lastItem = 0;

    /**
     * Constructor for a Tasklist
     */
    public Tasklist() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Marks a target task in the Tasklist as complete and returns it
     *
     * @param index The index (as shown on the UI) of the task to be completed
     * @return The completed task
     */
    public Task markAsDone(int index) {
        // index should be at least 1
        assert index >= 1 : "Target index for markAsDone should be at least 1!";

        Task task = this.taskList.get(index - 1);
        task.completeTask();
        return task;
    }

    /**
     * Adds a task to the Tasklist
     *
     * @param task The task to be added to the Tasklist
     */
    public void addTask(Task task) {
        taskList.add(lastItem, task);
        this.lastItem++;
    }

    /**
     * Returns the total number of tasks in the Tasklist
     *
     * @return The total number of tasks in the Tasklist
     */
    public int getTotalTasks() {
        return this.lastItem;
    }

    /**
     * Deletes the target task by index
     *
     * @param index The target task's index (as shown on the UI)
     * @return The deleted task
     */
    public Task deleteTask(int index) {
        // index should be at least 1
        assert index >= 1 : "Target index for deleteTask should be at least 1!";

        Task removed = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        this.lastItem--;

        assert this.lastItem >= 0 : "Index of last item cannot be less than 0!";

        return removed;
    }

    /**
     * Returns the list of all Tasks.
     *
     * @return An ArrayList containing all Tasks in the Tasklist
     */
    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }

    /**
     * Returns the String representation of the Tasklist, with
     * each task numbered starting from 1.
     *
     * @return A String representation of the Tasklist
     */
    @Override
    public String toString() {
        String contents = "";
        for (int i = 0; i < this.lastItem; i++) {
            String index = String.valueOf(i + 1);
            contents += (i != this.lastItem - 1)
                ? (index + ". " + this.taskList.get(i) + "\n")
                : (index + ". " + this.taskList.get(i));
        }

        return contents;
    }

    /**
     * Returns all Tasks whose descriptions contain any of the provided search terms.
     *
     * @param searchTerms A String describing the search terms to check the Tasks against.
     * @return An ArrayList of Tasks whose descriptions contain at least one of the search terms.
     */
    public Tasklist findAllTasksWith(String searchTerms) {
        Tasklist result = new Tasklist();

        assert !searchTerms.equals("") : "Search terms cannot be empty!";

        // Parse searchTerms
        String[] searchTokens = searchTerms.strip().split(" ");

        for (int i = 0; i < this.lastItem; i++) {
            Task current = this.taskList.get(i);

            // Check if any search tokens are in the task description
            for (String token : searchTokens) {
                String taskDescription = current.getDescription();

                if (taskDescription.contains(token)) {
                    result.addTask(current);
                    break;
                }
            }
        }

        return result;
    }
}
