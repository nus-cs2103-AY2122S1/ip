import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void markAsDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    public int getNumTask() {
        return this.taskList.size();
    }

    public String toString() {
        String description = "";
        for (int i = 0; i < this.taskList.size(); i += 1) {
            description += (i + 1) + "." + this.taskList.get(i) + "\n";
        }
        //To remove last new line
        description = description.substring(0, description.length() - 1);
        return description;
    }
}