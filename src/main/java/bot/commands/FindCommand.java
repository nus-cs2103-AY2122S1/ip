package bot.commands;

import static bot.constants.GlobalStringFormats.LINE_BREAK;
import static bot.constants.GlobalStringFormats.TAB_SPACES;

import java.util.List;

import bot.tasks.Task;
import bot.utility.TaskList;

/**
 * Represents a command to find tasks.
 */
public class FindCommand extends Command {
    private static final String LIST_TASK_FORMAT = LINE_BREAK + TAB_SPACES + " %d. %s";
    private final String keyWord;

    /**
     * Returns a FindCommand with the specified key word.
     *
     * @param keyWord The specified key word to be used for finding tasks.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Executes the Command and returns a String.
     *
     * @return A String to show to the user after execution of the FindCommand.
     */
    @Override
    public String execute() {
        message = new StringBuilder();
        message.append(LINE_BREAK).append("Here are the matching tasks in your list:");
        List<Task> tasks = TaskList.showTasks();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyWord)) {
                message.append(String.format(LIST_TASK_FORMAT, (i + 1), tasks.get(i)));
            }
        }
        return message.toString();
    }
}
