package duke.task;

import duke.processor.Executable;
import duke.processor.TaskList;

import java.io.Serializable;

/**
 * Task type for the duke bot.
 * 
 * @author Tianqi-Zhu
 */
public class Task implements Executable, Serializable {
    protected String name; 
    protected boolean isDone = false; 

    public Task(String name) {
        this.name = name; 
    }
    
    public void done() {
        isDone = true; 
    }

    public String name() {
        return name;
    }
    
    /**
     * Execute to add this task to the list.
     * 
     * @param taskList Current list of tasks.
     */
    public String execute(TaskList taskList) {
        return taskList.addTask(this);
    }

    public String toString() {
        if (isDone) {
            return "[X] " + name; 
        } else {
            return "[ ] " + name; 
        }
    }
}