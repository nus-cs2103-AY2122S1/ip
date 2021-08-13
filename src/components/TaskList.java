package components;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /*
     *  Returns true if task is added successfully, false otherwise.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public String showTask(int index) {
        return this.taskList.get(index).toString();
    }

    public int size() {
        return this.taskList.size();
    }

    public boolean markTaskDone(int index) {
        // index should be between 0 and n-1
        boolean changed = this.taskList.get(index).markDone();
        return changed;
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
