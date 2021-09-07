package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class HelpCommand implements Command {
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String helpMessage = "Bye: end the program \n"
                    + "Delete: delete a task \n"
                    + "Done: mark a task as done \n"
                    + "Find: find a task \n"
                    + "List: get a list of tasks \n"
                    + "schedule: get tasks of a certain day\n";
        return helpMessage;
    }
}
