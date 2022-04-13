package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Add task to taskList
     *
     * @param task the task want to add to the list
     */
    public void add(Task task) {
        taskList.add(task);
    }

    public Integer size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void remove(Task task) {
        taskList.remove(task);
    }

}
