package duke;

import java.util.ArrayList;
import java.io.Serializable;

public class TaskList implements Serializable{
    private ArrayList<Task> taskList; 

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList; 
    }

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> tasks() {
        return taskList;
    }

    public int taskAmount() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
        Ui.printString("Got it. I've added this task:\n  " + Ui.SPACE_STRING + task + "\n" + Ui.SPACE_STRING + "Now you have " + taskList.size() + " tasks in the list.");
        Storage.save(this);
    }

    public void deteteTask(int index) {
        Task removedTask = taskList.remove(index);
        Ui.printString("Noted. I've removed this task:\n  " + Ui.SPACE_STRING + removedTask + "\n" + Ui.SPACE_STRING +
             "Now you have " + taskList.size() + " tasks in the list.");
        Storage.save(this);
    }
}