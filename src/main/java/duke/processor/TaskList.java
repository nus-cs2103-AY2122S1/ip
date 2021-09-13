package duke.processor;

import duke.task.Task;

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
     * Add a task to the end of the list. Print the task name and total task amount in the list after adding. For Gui.
     * 
     * @param task Task to add to the list. 
     */
    public String addTask(Task task) {
        taskList.add(task);
        String out = "Got it. I've added this task:\n  " + Ui.SPACE_STRING + task + "\n" + Ui.SPACE_STRING +
                "Now you have " + taskList.size() + " tasks in the list.";
        Storage.save(this);
        return out;
    }
    
    /**
     * Delete a task by its index. Print the task name and total task amount in the list after adding. For Gui.
     * 
     * @param index Index of the task to be deleted. 
     */
    public String deteteTask(int index) {
        Task removedTask = taskList.remove(index - 1);
        Storage.save(this);
        String out = "Noted. I've removed this task:\n  " + Ui.SPACE_STRING + removedTask + "\n" + Ui.SPACE_STRING +
             "Now you have " + taskList.size() + " tasks in the list.";
        return out;
    }

    /**
     * Search if there are tasks with a given keyword. For Gui.
     *
     * @param keyword keyword to search
     * @return searched list
     */
    public String find(String keyword) {
        ArrayList<Task> outList = new ArrayList<Task>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).name().indexOf(keyword) >= 0) {
                outList.add(taskList.get(i));
            }
        }
        if (outList.size() == 0) {
            return "No current task found with a keyword";
        } else {
            String out = "Here are the tasks in your list with keyword " + keyword + ":\n";
            for (int i = 0; i < outList.size(); i++) {
                int index = i + 1;
                out = out + Ui.SPACE_STRING + index + "." + outList.get(i) + "\n";
            }
            return out;
        }
    }
}