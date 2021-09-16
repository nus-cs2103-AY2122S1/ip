package duke.commands;

import duke.tasks.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for the find command.
     *
     * @param keyword Keyword users are searching for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        StringBuilder op = new StringBuilder();

        for (int i = 0; i < tasks.getNumOfTasks(); i++) {
            if (tasks.getTask(i).descContains(keyword)) {
                op.append(tasks.getTask(i).toString()).append("\n");
            }
        }
        return op.length() == 0
                ? "Yikes! No tasks match what you are looking for :("
                : "Here are the matching tasks in your list:\n" + op;
    }
}
