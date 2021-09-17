package duke.command;

import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.TaskList;
import duke.ui.Ui;

public class ArchiveCommand implements Command {
    public static final String COMMAND_IDENTIFIER = "archive";

    private String fileName;

    /**
     * Returns the Archive command represented by the user input.
     *
     * @param userInput String input provided by the user.
     * @return Archive command.
     */
    public static Command create(String userInput)  {
        assert userInput != null : "User input should be null for the creation of a Command";

        String fileName = userInput.split(" ", 2)[1];
        return new ArchiveCommand(fileName);
    }

    private ArchiveCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            Storage.saveTasksToFile(tasks, fileName);
            tasks.clear();
            return ui.showTasksArchivedMessage(fileName);
        } catch (StorageException e) {
            return ui.showErrorMessage(e);
        }
    }
}
