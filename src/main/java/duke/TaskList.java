package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        tasks = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> taskData) {
        tasks = taskData;
    }
    
    public Task addTask(Task newTask) {
        tasks.add(newTask);
        return newTask;
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index - 1);
    }
    
    public int getTaskCount() {
        return tasks.size();
    }
    
    @Override
    public String toString() {
        StringBuilder tempBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            tempBuilder.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }
        return tempBuilder.toString();
    }
    
    public ArrayList<String> toBackupFormat() {
        ArrayList<String> backupData = new ArrayList<>();
        for (Task t: tasks) {
            backupData.add(t.toBackupFormat());
        }
        return backupData;
    }
    
    public TaskList findTask(String s) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(s)) {
                res.add(task);
            }
        }
        return new TaskList(res);
    }
}
