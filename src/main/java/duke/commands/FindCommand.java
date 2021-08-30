package duke.commands;

import java.util.ArrayList;

import duke.TaskList;
import duke.tasks.Task;

public class FindCommand extends Command {
    private String keyword;
    /**
     * Constructor for Command.
     *
     * @param keyword the keywords entered.
     */
    public FindCommand(String desc, String keyword) {
        super(desc);
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks) {
        ArrayList<Task> filtered = tasks.filter(keyword);

        StringBuilder replyBuilder = new StringBuilder();

        if (filtered.size() == 0) {
            replyBuilder.append("Unfortunately, no match can be found.\n");
        } else {
            replyBuilder.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < filtered.size(); i++) {
                replyBuilder.append((i + 1) + ". " + filtered.get(i) + "\n");
            }
        }

        return replyBuilder.toString();
    }

}
