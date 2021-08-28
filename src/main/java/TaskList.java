import java.util.ArrayList;
import java.util.List;


public class TaskList {
    protected List<Task> tasklist;

    public TaskList(ArrayList tasklist) {
        this.tasklist = tasklist;
    }

    public void addTask(Task task) {
        this.tasklist.add(task);
    }

    public String addTaskToString(Task task) {
        return ("Got it. I've added this task: \n" +
                task.toString()
                + "\nNow you have " + this.tasklist.size() + " tasks in the list.");
    }

    public void removeTask(int index) {
        this.tasklist.remove(index);
    }

    public String removeTaskToString(Task task) {
        return ("Noted. I've removed this task: \n" +
                task.toString()
                + "\nNow you have " + this.tasklist.size() + " tasks in the list.");
    }

    public Task getTask(int index) {
        return this.tasklist.get(index);
    }

    public int getSize() {
        return this.tasklist.size();
    }

    public void printTaskList() {
        if (this.tasklist.size() == 0) {
            System.out.println("There are currently no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.tasklist.size(); i++) {
                System.out.println((i + 1) + "." + this.tasklist.get(i).toString());
            }
        }
    }
}
