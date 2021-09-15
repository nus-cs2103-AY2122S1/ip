package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;

public class FindCommand extends Command {

    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:";
    private static final String NO_MATCH_MESSAGE = "Sorry no tasks with keyword found!";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String matchingTasks = tasks.find(this.keyword);

        if (matchingTasks.isEmpty()) {
            return NO_MATCH_MESSAGE;
        } else {
            String message = FIND_MESSAGE + "\n" + matchingTasks;

            return message;
        }
    }
}
