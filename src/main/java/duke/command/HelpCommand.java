package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class HelpCommand implements Command {
    /** Displays help information */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String helpMessage = "    bye: end the program \n"
                    + "    delete: delete a task \n"
                    + "    done: mark a task as done \n"
                    + "    find: find a task \n"
                    + "    list: get a list of tasks \n"
                    + "    schedule: get tasks of a certain day\n";
        return helpMessage;
    }
}
