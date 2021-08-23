package bern.functionalities;

import bern.model.Task;

import java.util.ArrayList;

public class TaskList {
    public void addTask(Task task, ArrayList<Task> arListTask) {
        arListTask.add(task);
    }

    public void removeTask(int index, ArrayList<Task> arListTask) {
        arListTask.remove(index);
    }
}