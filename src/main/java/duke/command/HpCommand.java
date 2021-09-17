package duke.command;

import duke.TaskList;
import duke.Ui;

public class HpCommand implements Command {

    @Override
    public String execute(TaskList tasks, Ui ui) {
        tasks.addHp();
        return ".....";
    }

    /**
     * Indicates if the command is an exit command.
     *
     * @return If the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
