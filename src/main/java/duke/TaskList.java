package duke;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Tasklist for duke. 
 * 
 * @author Tianqi-Zhu
 */
public class TaskList implements Serializable {
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

    /**
     * Add a task to the end of the list. Print the task name and total task amount in the list after adding.
     * 
     * @param task Task to add to the list. 
     */
    public void addTask(Task task) {
        taskList.add(task);
        Ui.printString("Got it. I've added this task:\n  " + Ui.SPACE_STRING + task + "\n" + Ui.SPACE_STRING + "Now you have " + taskList.size() + " tasks in the list.");
        Storage.save(this);
    }
    
    /**
     * Delete a task by its index. Print the task name and total task amount in the list after adding.
     * 
     * @param index Index of the task to be deleted. 
     */
    public void deteteTask(int index) {
        Task removedTask = taskList.remove(index);
        Ui.printString("Noted. I've removed this task:\n  " + Ui.SPACE_STRING + removedTask + "\n" + Ui.SPACE_STRING +
             "Now you have " + taskList.size() + " tasks in the list.");
        Storage.save(this);
    }
}