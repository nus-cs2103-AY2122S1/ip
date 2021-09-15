package duke.commands;

import duke.tasks.EventTask;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for event tasks
 */
public class EventCommand extends Command {
    private String commandString;

    public EventCommand(String commandString) {
        this.commandString = commandString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        String[] dateArray = commandString.split("/");
        String date = "";
        for (int i = 1; i < dateArray.length; i++) {
            if (i == 1) {
                date += dateArray[1].substring(3);
            } else {
                date += "/" + dateArray[i];
            }
        }

        String at = commandString.split("/")[1].substring(3);
        EventTask newEvent = new EventTask(commandString.substring(6).split("/")[0], date);
        taskList.addTask(newEvent);
        ui.printResponse("Got it. I've added this task: ");
        ui.printResponse("  " + newEvent.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
