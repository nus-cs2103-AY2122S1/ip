package duke.command;

import duke.processor.Executable;
import duke.processor.TaskList;

/**
 * Command that deletes a task from the list by its index. 
 * 
 * @author Tianqi-Zhu
 */
public class DeleteCommand implements Executable {
    private int index;

    public DeleteCommand(int index) {
        this.index = index; 
    }
    
    /**
     * Returns the message to be displayed by the bot after removing the task from the list.
     * 
     * @param tasklist current list of tasks.
     */
    public String execute(TaskList tasklist) {
        return tasklist.deteteTask(index);
    }
}