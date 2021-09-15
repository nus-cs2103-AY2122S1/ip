package jarvis.command;

import jarvis.exception.InvalidInputException;
import jarvis.exception.JarvisException;
import jarvis.message.OutputMessage;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Encapsulates the find command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param userInputWithoutCommandTrigger User input without the command trigger.
     * @throws InvalidInputException If the keyword is empty.
     */
    public FindCommand(String userInputWithoutCommandTrigger) throws InvalidInputException {
        this.keyword = userInputWithoutCommandTrigger.trim();
        if (this.keyword.equals("")) {
            throw new InvalidInputException("keyword");
        }
    }

    /**
     * Shows the matching tasks in the list to the user.
     *
     * @param taskList The list in which the tasks are stored.
     * @param storage Storage to save or load tasks to hard-disk.
     * @param ui Ui to show information to the user.
     * @return A OutputMessage that needs to be shown to the user after execution.
     * @throws JarvisException If the list is empty.
     */
    @Override
    public OutputMessage execute(TaskList taskList, Storage storage, Ui ui) throws JarvisException {
        if (taskList.getTaskListSize() == 0) {
            throw new JarvisException("Your list is currently empty! Try adding some tasks before searching!");
        }
        return ui.getMatchingTaskListMessage(taskList.getListWithKeyword(keyword));
    }
}
