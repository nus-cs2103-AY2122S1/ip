package duke;

import duke.tasks.Task;

import java.util.ArrayList;

// Class that handles the storing of Tasks
public class Tasklist {
    private ArrayList<Task> taskList;
    private int lastItem = 0;

    // Constructor for a list
    public Tasklist() {
        this.taskList = new ArrayList<>();
    }

    // Mark a certain task as done
    public Task markAsDone(int index) {
        this.taskList.get(index - 1).completeTask();
        return this.taskList.get(index - 1); 
    }

    // Method to add a task to the list
    public void addTask(Task task) {
        taskList.add(lastItem, task);
        this.lastItem++;
    }

    // Get number of tasks in list
    public int getTotalTasks() {
        return this.lastItem;
    }

    // Method for deletion of tasks
    public Task deleteTask(int index) {
        Task removed = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        this.lastItem--;
        return removed;
    }

    // Method to get list of all tasks
    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }

    // String representation of the tasklist
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
     * @param searchTerms A String describing the search terms to check the Tasks against
     * @return An ArrayList of Tasks whose descriptions contain at least one of the search terms.
     */
    public Tasklist findAllTasksWith(String searchTerms) {
        Tasklist result = new Tasklist();

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
