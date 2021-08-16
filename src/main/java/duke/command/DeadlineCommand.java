package duke.command;

import duke.Duke;
import duke.task.DeadlineTask;

/**
 * A command to add a deadline task into main DukeList.
 */
public class DeadlineCommand implements Command {

    @Override
    public void exec(String args) {
        Duke.getList().addWithResponse(new DeadlineTask(args)).print();
    }

    @Override
    public String getLabel() {
        return "deadline";
    }

}
