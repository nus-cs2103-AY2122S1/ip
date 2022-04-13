package duke.command;

import duke.processor.Executable;
import duke.processor.TaskList;

/**
 * Command to find tasks with a keyword.
 * 
 * @author Tianqi-Zhu
 */
public class FindCommand implements Executable {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    
    /**
     * Returns the tasks found after finding the tasks with the keyword.
     * 
     * @param taskList current taskList
     */
    public String execute(TaskList taskList) {
        return taskList.find(keyword);
    }
}
