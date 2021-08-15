/**
 * This is the Tasklist class.
 * It stores Tasks as a list.
 */
public class Tasklist {
    private Task[] list;
    private int numTasks;

    public Tasklist() {
        this.list = new Task[100]; // list of size 100
        this.numTasks = 0;
    }

    public Task GetTaskAt(int n) {
        return this.list[n-1];
    }

    public Boolean isEmpty() {
        return numTasks == 0;
    }

    public void addTask(String description) { // adds new task to taskList
        Task newTask = new Task(description);
        this.list[numTasks] = newTask;
        numTasks += 1;
    }

    public void markDone(int n) { // need some adjustments.
        this.list[n - 1].markAsDone();
    }

    public String toString() { // displays the list
        String result = "";
        for (int i = 0; i < numTasks; i++) {
            result += i + 1 + ". " + list[i] + "\n";
        }
        return result;
    }

}
