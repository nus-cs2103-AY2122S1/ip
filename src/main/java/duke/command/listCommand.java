package duke.command;

import duke.Storage;
import duke.task.TaskList;

public class listCommand extends Command {
    private String command;
    
    public listCommand(String command) {
       super(command); 
       this.command = command;
    }

    public String toString() {
        return "This is a list command";
    }
    
    public void execute(TaskList taskList, Storage storage) {
        taskList.getList();
    }
}