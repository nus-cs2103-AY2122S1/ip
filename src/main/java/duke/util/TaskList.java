package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    @Override
    public String toString() {
        String taskListString = "";
        for (int i = 0; i < taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                taskListString = taskListString + "     " + (i + 1) + "." + taskList.get(i);
            } else {
                taskListString = taskListString + "     " + (i + 1) + "." + taskList.get(i) + "\n";
            }
        }
        return taskListString;
    }
}
