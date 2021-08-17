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
}
