// Class that handles the storing of Tasks
public class Tasklist {
    private Task[] taskList;
    private int lastItem = 0;

    // Constructor for a list
    public Tasklist() {
        this.taskList = new Task[100];
    }

    // Mark a certain task as done
    public Task markAsDone(int index) {
        this.taskList[index - 1].completeTask();
        return this.taskList[index - 1];
    }

    // Method to add a task to the list
    public void addTask(Task task) {
        taskList[lastItem] = task;
        lastItem++;
    }

    // String representation of the tasklist
    @Override
    public String toString() {
        String contents = "";
        for (int i = 0; i < this.lastItem; i++) {
            String index = String.valueOf(i + 1);
            contents += (i != this.lastItem - 1) 
                ? (index + ". " + this.taskList[i] + "\n") 
                : (index + ". " + this.taskList[i]);
        }

        return contents;
    }
}
