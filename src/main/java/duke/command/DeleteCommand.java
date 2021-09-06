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
     * Execute the delete command. 
     * 
     * @param tasklist current list of tasks.
     */
    public String execute(TaskList tasklist) {
        return tasklist.deteteTask(index);
    }
}