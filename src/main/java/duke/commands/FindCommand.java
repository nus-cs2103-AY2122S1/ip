package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;

public class FindCommand extends Command {

    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String message = FIND_MESSAGE + "\n" + tasks.find(this.keyword);

        return message;
    }
}
