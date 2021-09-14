package catobot.command;

import java.util.function.Predicate;

import catobot.Storage;
import catobot.exception.BotException;
import catobot.exception.InvalidCommandException;
import catobot.item.Deadline;
import catobot.item.Event;
import catobot.item.Task;
import catobot.item.TaskList;
import catobot.item.TaskType;
import catobot.item.Todo;

/**
 * Represents the command to sort a type of tasks.
 */
public class SortCommand extends Command {

    private final TaskType taskType;

    /**
     * Constructor for SortCommand.
     *
     * @param content The content of the command.
     */
    protected SortCommand(String content) {
        String name = Parser.parseSingleArgument(content, CommandType.SORT);
        this.taskType = TaskType.find(name);
    }

    /**
     * Sorts a specific type of tasks.
     *
     * @param tasks The list of tasks to be worked on.
     * @param storage The storage of the tasks.
     * @return If taskType is deadline or event, sort chronologically with the latest first.
     *         If taskType is todo, sort lexicographically.
     * @throws BotException If the command is not valid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BotException {
        Predicate<Task> condition;
        switch (taskType) {
        case DEADLINE:
            condition = ((t) -> t instanceof Deadline);
            break;
        case EVENT:
            condition = ((t) -> t instanceof Event);
            break;
        case TODO:
            condition = ((t) -> t instanceof Todo);
            break;
        default:
            throw new InvalidCommandException();
        }

        return tasks.sort(taskType, condition);
    }

}
