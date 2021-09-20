package duke.command;

import duke.Task;
import duke.TaskList;
import duke.ui.UserInterface;

public class CommandShowList extends Command {
    public static final String SHOW_LIST_NAME = "list";

    public CommandShowList () {
        super();
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui){
        ui.print(taskList.toString());
    }
}
