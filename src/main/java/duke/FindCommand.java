package duke;

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
     * Find the print the task when executed.
     * 
     * @param taskList current taskList
     */
    public void execute(TaskList taskList) {
        taskList.find(keyword);
    }
}
