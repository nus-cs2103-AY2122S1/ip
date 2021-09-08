package duke.command;

import duke.Message;
import duke.TaskList;
import duke.storage.TaskStorage;
import duke.ui.Ui;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage taskStorage) {
        String invalidCommandMessage = Message.getInvalidCommandMessage();
        ui.setCurrentMessage(invalidCommandMessage);
    }

}
