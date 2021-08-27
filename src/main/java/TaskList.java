import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        tasks = new ArrayList<Task>();
    }
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasksOccurringOn(LocalDate date) {
        ArrayList<Task> relevantTasks = new ArrayList<Task>();
        for (Task task: tasks) {
            if ((task instanceof Deadline && ((Deadline) task).by.equals(date))
                    || (task instanceof Event  && ((Event) task).at.equals(date))) {
                relevantTasks.add(task);
            }
        }
        return relevantTasks;
    }

    public int getLength() {
        return tasks.size();
    }
}
