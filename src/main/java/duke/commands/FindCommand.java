package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand given the search query.
     *
     * @param keyword The search query.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.find(keyword);
        return message;
    }
}
