package duke.task;

import java.util.ArrayList;

public class TaskList {

    /** Stores tasks in an array list */
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskList) {
        tasks = taskList;
    }

    public static TaskList emptyTaskList() {
        return new TaskList(new ArrayList<Task>());
    }

    public boolean addTask(Task t) {
        return tasks.add(t);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String listOfTasks = "\n";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks = listOfTasks + String.format("\t%d. %s\n", i + 1, tasks.get(i));
        }

        return listOfTasks;
    }
}
