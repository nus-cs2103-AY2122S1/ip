import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfTasks;

    TaskList() {
        listOfTasks = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> lst) {
        listOfTasks = lst;
    }

    void add(Task task) {
        listOfTasks.add(task);
    }

    Task delete(int taskNumber) {
        return listOfTasks.remove(taskNumber);
    }

    void done(int taskNumber) {
        Task currTask = listOfTasks.get(taskNumber);
        currTask.markAsDone();
    }

    int size() {
        return listOfTasks.size();
    }

    ArrayList<Task> getAllTasks() {
        return listOfTasks;
    }
}
