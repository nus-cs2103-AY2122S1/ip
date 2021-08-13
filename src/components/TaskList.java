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
    public boolean addTask(Task task) {
        taskList.add(task);
        return true;
    }

    @Override
    public String toString() {
        String returnString = "";
        for (int i = 1; i < taskList.size() + 1; i++) {
            String row = String.format("%d. %s \n", i, taskList.get(i-1).toString());
            returnString += row;
        }
        return  returnString;
    }
}
