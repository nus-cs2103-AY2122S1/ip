package components;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    private TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


    /*
     *  Returns true if task is added successfully, false otherwise.
     */
    public TaskList addTask(Task task) {
        this.taskList.add(task);
        return new TaskList(this.taskList);
    }

    public String showTask(int index) {
        return this.taskList.get(index).toString();
    }

    public int size() {
        return this.taskList.size();
    }

    public TaskList markTaskDone(int index) {
        // index should be between 0 and n-1
        this.taskList.set(index, this.taskList.get(index).markDone());
        return new TaskList(this.taskList);
    }

    @Override
    public String toString() {
        String returnString = "";
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            String row = String.format("%d. %s", i, this.taskList.get(i-1).toString());
            if (i != this.taskList.size()) {
                row += "\n";
            }
            returnString += row;
        }
        return  returnString;
    }
}
