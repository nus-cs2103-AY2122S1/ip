import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> al;

    public TaskList() {
        this.al = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        this.al.add(t);
    }

    @Override
    public String toString() {
        String result = "-----------------------------------------\n";
        Task[] taskArray = this.al.toArray(new Task[0]);
        for (int i = 0; i < taskArray.length; i++) {
            result += String.format("%s. %s", i+1, taskArray[i].toString());
        }
        return result + "-----------------------------------------\n";
    }
}
