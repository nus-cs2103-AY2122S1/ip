package duke.command;

import duke.ArchiveList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.IncompleteFindException;

/**
 * Represents the find command of Duke.
 */
public class FindCommand extends Command {

    /**
     * Represents keyword to find.
     */
    private final String keyword;

    /**
     * Constructs a FindCommand object with keyword to find in taskList.
     *
     * @param keyword Keyword to search for in TaskList.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Generates a FindCommand from the userInput.
     *
     * @param userInput User Input which is used to generate the FindCommand.
     * @return FindCommand to be executed.
     * @throws IncompleteFindException if insufficient values are passed in.
     */
    public static FindCommand generateCommand(String userInput) throws IncompleteFindException {
        String[] separated = userInput.split(SPACE);

        if (separated.length == 1) {
            throw new IncompleteFindException();
        }

        String keyword = separated[1];

        return new FindCommand(keyword);
    }

    /**
     * Checks if FindCommand is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the FindCommand.
     *
     * @param taskList TaskList object to find keyword from.
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui Object to print to user.
     * @param storage Storage object which saves and loads the tasklist.
     */
    @Override
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        TaskList keywordTasks = taskList.findTasksWithKeyword(this.keyword);
        ui.printKeywordTasks(keywordTasks, this.keyword);
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute the finding of the keyword.
     *
     * @param taskList TaskList object to find keyword from.
     * @param archiveList ArchiveList to store archived tasks.
     * @param ui Ui Object to get the String representation of the text printed.
     * @param storage Storage object which saves and loads the tasklist.
     * @return String representation of the things printed in the execute method.
     */
    @Override
    public String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        TaskList keywordTasks = taskList.findTasksWithKeyword(this.keyword);
        return ui.formatPrintKeywordTasksString(keywordTasks, this.keyword);
    }
}
