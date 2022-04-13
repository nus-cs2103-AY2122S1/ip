package duke.command;

import duke.processor.Executable;
import duke.processor.Storage;
import duke.processor.TaskList;
import duke.processor.Ui;

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
     * Returns the name if the task after marking a task as done.
     *
     * @param taskList Current list of tasks.
     */
    public String execute(TaskList taskList) {
        taskList.tasks().get(index - 1).done();
        Storage.save(taskList);
        return "Nice, I've marked this as done!\n" + Ui.SPACE_STRING + "  " + taskList.tasks().get(index - 1);
    }
}