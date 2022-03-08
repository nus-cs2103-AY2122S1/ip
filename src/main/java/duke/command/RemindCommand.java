package duke.command;

import duke.Message;
import duke.TaskList;
import duke.exception.DukeException;
import duke.storage.TaskStorage;
import duke.ui.Ui;

public class RemindCommand extends Command {
    private int numberOfDays;

    public RemindCommand(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage taskStorage) throws DukeException {
        TaskList upcomingTaskList = taskList.getUpcomingTaskList(numberOfDays);
        String upcomingTaskListMessage = Message.getUpcomingTaskListMessage(upcomingTaskList);
        ui.setCurrentMessage(upcomingTaskListMessage);
    }

}
