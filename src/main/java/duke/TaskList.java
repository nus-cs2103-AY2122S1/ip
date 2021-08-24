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
    
    /**
     * Search if there are tasks with a given keyword
     * 
     * @param keyword keyword to search
     * @return searched list
     */
    public void find(String keyword) {
        ArrayList<Task> outList = new ArrayList<Task>(); 
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).name().indexOf(keyword) >= 0) {
                outList.add(taskList.get(i));
            }
        }
        if (outList.size() == 0) {
            Ui.printString("No current task found with a keyword " + keyword);
        } else {
            String out = "Here are the tasks in your list:\n";
            for (int i = 0; i < outList.size(); i++) {
                int index = i + 1;
                out = out + Ui.SPACE_STRING + index + "." + outList.get(i) + "\n";
            }
            Ui.printList(out);
        }
    }
}