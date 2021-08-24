package duke;

/**
 * Command that marks a task as done by its index. 
 * 
 * @author Tianqi-Zhu
 */
public class DoneCommand implements Executable {
    private int index;

    public DoneCommand(int index) {
        this.index = index; 
    }
    
    /**
     * Execute the command to mark a task as done. 
     * 
     * @param taskList Current list of tasks.
     */
    public void execute(TaskList taskList) {
        taskList.tasks().get(index - 1).done();
        Storage.save(taskList); 
        Ui.printString("Nice, I've marked this as done!\n" + Ui.SPACE_STRING + "  " + taskList.tasks().get(index - 1));
    }
}