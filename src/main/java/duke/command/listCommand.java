package duke.command;

import duke.task.TaskList;

import duke.Storage;

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