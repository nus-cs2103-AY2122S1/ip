package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.HashSet;

public class FindCommand extends Command {

    private HashSet<String> keywords = new HashSet<>();
    public FindCommand(String[] keywords) {
        for (String keyword : keywords) {
            this.keywords.add(keyword);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(this.keywords, ui);
    }
}
