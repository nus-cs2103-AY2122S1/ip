package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.Ui;

/**
 * This class represents a command to list all the tasks.
 */
public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        String[] tasksText = tasks.getStringArr();
        int len = tasksText.length;
        for (int i = 0; i < len; i++) {
            tasksText[i] = (i + 1) + ". " + tasksText[i];
        }
        Ui.printFormattedReply(tasksText);
    }

    /**
     * Returns true if command is an exit command.
     *
     * @return true if command is an exit command, false otherwise
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
