package duke.task;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tl) {
        taskList = tl;
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void doneTask(int index) {
        taskList.get(index).markAsDone();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public TaskList searchList(String target) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : taskList) {
            if (t.toString().contains(target)) {
                foundTasks.add(t);
            }
        }
        return new TaskList(foundTasks);
    }
}