package duke.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> lst) {
        listOfTasks = lst;
    }

    public void add(Task task) {
        listOfTasks.add(task);
    }

    public Task delete(int taskNumber) {
        return listOfTasks.remove(taskNumber);
    }

    public void done(int taskNumber) {
        try {
            Task currTask = listOfTasks.get(taskNumber);
            currTask.markAsDone();
        } catch (IndexOutOfBoundsException ioobe) {
            return;
        }
    }

    public int size() {
        return listOfTasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return listOfTasks;
    }
}
