package duke.commands;

import java.util.HashSet;

import duke.utils.CliUi;
import duke.utils.Storage;
import duke.utils.TaskList;

public class FindCommand extends Command {

    private HashSet<String> keywords = new HashSet<>();

    /**
     * FindCommand constructor.
     *
     * @param keywords Keywords to search for within task list.
     */
    public FindCommand(String[] keywords) {
        String[] keyWordsSplit = keywords[0].split(" ");
        for (String keyword : keyWordsSplit) {
            this.keywords.add(keyword);
        }
        assert (keywords.length > 0) : "keywords should contain at least 1 keyword.";
    }

    /**
     * Finds all tasks in the taskList that matches all of the specified keywords.
     *
     * @param taskList The list of tasks in Duke. Handles all task related functions.
     * @param cliUi Ui object to deal with user input/outputs.
     * @param storage Storage object to deal with saving taskList to disk.
     * @return String[] with the messages to be printed out to the Ui.
     */
    @Override
    public String execute(TaskList taskList, CliUi cliUi, Storage storage) {
        return taskList.find(keywords, cliUi);
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String representation of the command.
     */
    @Override
    public String toString() {
        return "TO FIND: All tasks containing all of these keywords, " + keywords.toString();
    }
}
