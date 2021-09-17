package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for
 * marking a task as done
 */
public class DoneCommand extends Command {

    private String commandString;

    public DoneCommand(String commandString) {
        this.commandString = commandString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        String[] commandArr = commandString.split(" ");
        try {
            int taskNumberDone = Integer.parseInt(commandArr[1]);
            if (taskNumberDone > taskList.numberOfTasks()) {
                ui.printResponse("Invalid number");
                return;
            } else {
                int taskDoneIndex = taskNumberDone - 1;
                taskList.getTask(taskDoneIndex).makeDone();
                ui.printResponse("Nice! I've marked this task as done: ");
                ui.printResponse(taskList.getTask(taskDoneIndex).toString());
            }
        } catch (Exception e) {
            ui.printResponse("Invalid input");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
