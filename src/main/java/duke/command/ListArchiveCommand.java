package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListArchiveCommand implements Command {

    @Override
    public String execute(TaskList taskList, Ui ui) {
        String hpMsg = "\n" + ui.getHpMsg(taskList.getHp());
        if (taskList.getArchiveSize() == 0) {
            return "There are no tasks in your archive yet.\n" + hpMsg;
        }
        StringBuilder response = new StringBuilder("Here are the tasks in your archive:\n");
        for (int i = 1; i < taskList.getArchiveSize() + 1; i++) {
            response.append(String.format("  %d. %s\n", i, taskList.getArchivedTask(i - 1)));
        }
        response.append(hpMsg);
        return response.toString();
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
