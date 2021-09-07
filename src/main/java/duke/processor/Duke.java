package duke.processor;

import duke.processor.TaskList;

/**
 * Duke type, to be used in
 * 
 * @author Tianqi-Zhu
 */
public class Duke {

    private TaskList taskList;

    public Duke(TaskList taskList) {
        this.taskList = taskList;
    }

    public TaskList getTaskList() {
        return taskList;
    }

}