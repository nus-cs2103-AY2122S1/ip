package bot.commands;

import static bot.constants.GlobalStringFormats.LINE_BREAK;
import static bot.constants.GlobalStringFormats.TAB_SPACES;

import java.util.List;

import bot.tasks.Deadline;
import bot.tasks.Event;
import bot.tasks.Task;
import bot.tasks.ToDo;
import bot.utility.TaskList;

/**
 * Represents a command to add tasks to the TaskList;
 */
public class AddCommand extends Command {
    private static final String ADD_TASK_FORMAT =
            "Got it. I've added this task:" + LINE_BREAK + TAB_SPACES + "%s" + LINE_BREAK;
    private final String keyWord;
    private final String info;

    /**
     * Returns a AddCommand with the specified keyWord and info.
     *
     * @param keyWord The key word to distinguish between different commands that add tasks to the TaskList;
     * @param info The necessary information about the tasks to be added.
     */
    public AddCommand(String keyWord, String info) {
        this.keyWord = keyWord;
        this.info = info;
    }

    /**
     * Executes the Command and returns a String.
     *
     * @return A String to show to the user after execution of the AddCommand.
     */
    @Override
    public String execute() {
        List<Task> tasks = TaskList.showTasks();
        switch (keyWord) {
        case "todo":
            ToDo todo = new ToDo(info);
            tasks.add(todo);
            return formatMessage(todo.toString(), tasks.size());
        case "deadline":
            String[] details = info.split(" /by ", 2);
            Deadline deadline = new Deadline(details[0], details[1]);
            tasks.add(deadline);
            return formatMessage(deadline.toString(), tasks.size());
        case "event":
            details = info.split(" /at ", 2);
            Event event = new Event(details[0], details[1]);
            tasks.add(event);
            return formatMessage(event.toString(), tasks.size());
        default:
            break;
        }
        return "Hmm, this is quite the predicament. I can't comprehend the instruction.";
    }

    private String formatMessage(String objectString, int size) {
        return String.format(ADD_TASK_FORMAT, objectString) + String.format(INFORM_FORMAT, size);
    }
}
