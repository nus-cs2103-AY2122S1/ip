package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the FindCommand in the Duke program.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Construct a FindCommand to find all task(s) with the given keyword in the list of tasks.
     *
     * @param keyword Keyword to be used to find all task(s) in the list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Defines the execution of the FindCommand where all task(s) with the given keyword is/are listed.
     *
     * @param tasks Tasks of the Duke program.
     * @param ui Ui of the Duke program.
     * @param storage Storage of the Duke program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currTask = tasks.getTask(i);
            if (currTask.toString().contains(keyword)) {
                matchingTasks.addTask(currTask);
            }
        }
        String response;
        if (matchingTasks.getSize() == 0) {
            response = "There are no matching tasks.";
        } else {
            response = "Here are the matching tasks in your list:\n" + matchingTasks;
        }
        ui.showResponse(response);
    }

    /**
     * Returns false as this command is not the ExitCommand.
     *
     * @return false as this command is not the ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
