package pilcrow;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void setTaskIsDone(int index, Boolean isDone) {
        index--;
        if (index < 0 || index >= this.taskList.size()) {
            throw new InvalidInputException("Must enter valid task number.");
        }
        this.taskList.get(index).setIsDone(isDone);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int index) {
        index--;
        if (index < 0 || index >= this.taskList.size()) {
            throw new InvalidInputException("Must enter valid task number.");
        }
        this.taskList.remove(index);
    }

    public Task getTask(int index) {
        index--;
        if (index < 0 || index >= this.taskList.size()) {
            throw new InvalidInputException("Must enter valid task number.");
        }
        return this.taskList.get(index);
    }

    public TaskList filteredTaskList(String string) {
        TaskList filteredTaskList = new TaskList();
        for (int i = 0; i < this.taskList.size(); i++) {
            if (taskList.get(i).containsString(string)) {
                filteredTaskList.addTask(taskList.get(i));
            }
        }
        return filteredTaskList;
    }

    @Override
    public String toString() {
        String tasksAsString = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasksAsString += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return tasksAsString;
    }

    public int getNumberOfTasks() {
        return this.taskList.size();
    }
}