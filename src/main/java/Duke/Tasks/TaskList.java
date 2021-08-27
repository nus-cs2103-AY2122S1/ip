package Duke.Tasks;

import Duke.Exceptions.IndexNotInListException;
import Duke.Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> taskList = new ArrayList<>();

    // Empty Constructor
    public TaskList() {

    }

    public TaskList(ArrayList<Task> list) {
        taskList.addAll(list);
    }

    public void insert(Task task) {
        taskList.add(task);
    }

    public Task complete(int index) throws IndexNotInListException {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexNotInListException("Haiyo, you sure there is a task " + index + " anot...");
        }
        return taskList.get(index).completeTask();
    }

    public Task delete(int index) throws IndexNotInListException {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexNotInListException("Haiyo, you sure there is a task " + index + " anot...");
        }
        return taskList.remove(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void deleteAll() {
        taskList.clear();
    }

    public int getSize() {
        return taskList.size();
    }
}
