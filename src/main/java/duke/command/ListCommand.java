package duke.command;

import duke.data.TaskList;

public class ListCommand extends Command {

    public ListCommand(TaskList tasklist){
        super(tasklist);
    }

    public void execute(){
        super.taskList.listTasks();
    }
}
