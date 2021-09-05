package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {

    private String terms;

    /**
     * Create a new Command indicating a task is to be found.
     * @param fullCommand Unedited user command.
     */
    public FindCommand(String fullCommand) throws DukeException {
        String terms = fullCommand.replace("find", "").trim();
        if (terms.isEmpty()) {
            throw new DukeException("Empty Find Terms");
        }
        this.terms = terms;
    }

    /**
     * Execute user commands.
     * @param tasks List of tasks.
     * @param ui Ui of Duke chatbot.
     * @param storage Storage of Duke chatbot.
     * @throws DukeException If execution fails.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the matching tasks in your list:";
        int listIndex = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = (Task) tasks.get(i);
            String taskString = task.toString();
            if (!taskString.contains(terms)) {
                continue;
            }
            response += String.format("\n%d. %s", listIndex++, task);
        }
        ui.showMessage(response);
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
        return false;
    }

}
