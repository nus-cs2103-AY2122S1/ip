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
    }

    @Override
    public String execute(TaskList tasks, CliUi cliUi, Storage storage) {
        // TODO: Make tasks.find return a String
        return tasks.find(keywords, cliUi);
    }
}
