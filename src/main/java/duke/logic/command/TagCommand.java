package duke.logic.command;

import duke.logic.tasks.TaskList;

public class TagCommand extends Command {
    private int index;
    private String tag;
    
    public TagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }
    
    @Override
    public String executeCommand(TaskList taskList) {
        return taskList.tagTask(index, tag);
    }
}
