package jarvis.command;

import jarvis.exception.InvalidInputException;
import jarvis.exception.JarvisException;
import jarvis.exception.UndoException;
import jarvis.message.OutputMessage;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

public class UndoCommand extends Command {
    private int numberOfCommandsToUndo;

    /**
     * Constructor for UndoCommand.
     *
     * @param userInputWithoutCommandTrigger User input without the command trigger.
     * @throws JarvisException If the user input is invalid.
     */
    public UndoCommand(String userInputWithoutCommandTrigger) throws JarvisException {
        try {
            this.numberOfCommandsToUndo = Parser.getNumberOfUndos(userInputWithoutCommandTrigger);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("number");
        }
    }

    /**
     * Reverts the history of the task list, rewrites the storage file and shows the ui message to user.
     *
     * @param taskList The list in which the tasks are stored.
     * @param storage Storage to save or load tasks to hard-disk.
     * @param ui Ui to show information to the user.
     * @return A OutputMessage that needs to be shown to the user after execution.
     * @throws JarvisException If there is an error.
     */
    @Override
    public OutputMessage execute(TaskList taskList, Storage storage, Ui ui) throws JarvisException {
        if (numberOfCommandsToUndo <= 0 || numberOfCommandsToUndo > taskList.getVersionHistorySize()) {
            throw new UndoException();
        }
        taskList.revertHistory(numberOfCommandsToUndo, storage);
        return ui.getUndoMessage(numberOfCommandsToUndo);
    }
}
