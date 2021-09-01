package duke.command;


import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Task;
import duke.TaskList;
import duke.TaskType;
import duke.ToDo;
import duke.Ui;
import duke.TaskList;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.displayGoodbyeMsg();
    }

    public boolean isExit() {
        return true;
    }
}
