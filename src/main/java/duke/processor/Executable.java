package duke.processor;

import duke.processor.TaskList;

/**
 * type to return after parsing
 * 
 * @author Tianqi-Zhu
 */
public interface Executable {
    public String execute(TaskList taskList);
}