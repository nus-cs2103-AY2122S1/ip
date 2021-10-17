package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String msg = "";
        if (taskList.getTotalNumberOfTask() > 0) {
            msg = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.getTotalNumberOfTask(); i++) {
                int index = i + 1;
                msg += index + "." + taskList.getTaskById(i) + "\n";
            }
            msg += "\n";
        } else {
            msg = ui.printErrorMessage("Looks like there isn't any task!");
            System.out.println(msg);
        }
        return msg;
    }
}
