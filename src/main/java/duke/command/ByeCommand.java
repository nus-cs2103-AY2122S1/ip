package duke.command;

import duke.Message;
import duke.TaskList;
import duke.storage.TaskStorage;
import duke.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage taskStorage) {
        String exitMessage = Message.getExitMessage();
        ui.setCurrentMessage(exitMessage);
        ui.setIsRunning(false);
    }

}
