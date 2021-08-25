package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getTotalNumberOfTask() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getTotalNumberOfTask(); i++) {
                int index = i + 1;
                System.out.println(index + "." + taskList.getTaskById(i));
            }
            System.out.println();
        } else {
            ui.printErrorMessage("Looks like there isn't any task!");
        }
        return true;
    }
}
