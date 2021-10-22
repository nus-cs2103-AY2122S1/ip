package duke.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.SortOrder;
import duke.TaskList;
import duke.Ui;

public class SortCommand extends Command {
    /**
     * Regex pattern for sorting commands
     */
    private static final Pattern PATTERN_SORT = Pattern.compile("^sort (\\w*)$");

    public SortCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks) {
        // Check if user is attempting to mark a task as done.
        Matcher matcher = PATTERN_SORT.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("You can sort the tasks chronologically or alphabetically!");
        }

        SortOrder order;
        switch (matcher.group(1)) {
        case "chronologically":
            order = SortOrder.CHRONOLOGICALLY;
            break;
        case "alphabetically":
            order = SortOrder.ALPHABETICALLY;
            break;
        default:
            throw new DukeException("You can only sort the tasks chronologically or alphabetically!");
        }

        // Remove the task from the store.
        tasks.sort(order);

        return Ui.notifySort(order) + "\n" + Ui.printTasks(tasks);
    }
}
