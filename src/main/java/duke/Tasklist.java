package duke;

import duke.tasks.Task;

import java.util.ArrayList;

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
        this.taskList.get(index - 1).completeTask();
        return this.taskList.get(index - 1); 
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
        Task removed = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        this.lastItem--;
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
}
