package duke;

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
     * @param Current list of tasks.
     */
    public void execute(TaskList tasklist) {
        tasklist.deteteTask(index);
    }
}