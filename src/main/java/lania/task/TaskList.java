package lania.task;

import lania.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<Task>();
    }

    public void update(Task t) {
        taskArrayList.add(t);
    }

    public void complete(int i) {
        i--;
        taskArrayList.get(i).markAsDone();
    }

    public Task remove(int i) {
        i--;
        Task t = taskArrayList.get(i);
        taskArrayList.remove(i);
        return t;
    }

    public int size() {
        return taskArrayList.size();
    }

    public Task get(int i) {
        return taskArrayList.get(i);
    }

    /**
     * Creates a new TaskList consisting only of tasks
     * that matches the keyword by looping through the
     * current list.
     *
     * @param s The keyword to match.
     * @return TaskList with only matching tasks.
     */
    public TaskList find(String s) {
        TaskList temp = new TaskList();
        for (int i = 0; i < taskArrayList.size(); i++) {
            if (taskArrayList.get(i).toString().contains(s)) {
                temp.update(taskArrayList.get(i));
            }
        }
        return temp;
    }
}
