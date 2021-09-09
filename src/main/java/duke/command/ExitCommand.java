package duke.command;

import duke.taskList.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    private boolean isExit;

    public ExitCommand(TaskList tasks, String input) {
        super(tasks, input);
        this.isExit = true;
    }

    @Override
    public boolean isExitCommand() {
        return isExit;
    }

    public String bye(Ui ui) {
        return ui.showBye();
    }

}