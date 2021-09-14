package bot.assembly.function;

import java.util.List;

import bot.assembly.error.InvalidCommandFormatException;
import bot.assembly.error.InvalidTaskIndexException;
import bot.assembly.error.TaskOutOfRangeException;
import bot.assembly.memory.BotDynamicMemoryUnit;
import bot.assembly.memory.BotStaticMemoryUnit;
import bot.assembly.task.Task;

public class BotCommandChecker {

    private final BotStaticMemoryUnit botStaticMemoryUnit = new BotStaticMemoryUnit();
    private final List<Task> taskTracker = BotDynamicMemoryUnit.getInstance().getTaskTacker();

    /**
     * Constructor
     */
    public BotCommandChecker() {}

    /**
     * A method that tokenizes the command input to 2 parts:
     * 1. Command Type
     * 2. Rest of the command
     * @param input command input
     * @return [Command Type, Rest of the command]
     */
    private String[] tokenize(String input) {
        return input.split(" ", 2);
    }

    /**
     * A method to check if the index entered by the user are truly an Integer:
     * This method is specifically used by command type of:
     * 1) Done
     * 2) Delete
     * @param str index of command input
     * @return true if command input's index can be converted to Integer
     */
    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * A method to check if the find task command is in the correct format:
     * "find keyword1 keyword2"
     * @param input command input
     * @throws InvalidCommandFormatException if the find task command is wrong
     */
    public void checkFindTaskFormat(String input) throws InvalidCommandFormatException {
        String[] inputToken = tokenize(input);

        if (inputToken.length == 1) {
            throw new InvalidCommandFormatException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * A method to check the validity of the index entered
     * This method is specifically used by command type of:
     * 1) Done
     * 2) Delete
     * @param input command input
     * @throws TaskOutOfRangeException if index entered is out of the range of the task list
     * @throws InvalidTaskIndexException if the command input's index entered cannot be converted to Integer
     */
    public void checkTaskListModificationCommand(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {

        // throw InvalidTaskIndexException if index entered is not an Integer
        if (!isInteger(input.split(" ", 2)[1])) {
            throw new InvalidTaskIndexException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_TASK_INDEX);
        }

        Integer index = Integer.parseInt(input.split(" ", 2)[1]);

        // throw TaskOutOfRangeException if the index entered is out of task list's range or when the task list is empty
        if (index - 1 >= taskTracker.size() || taskTracker.size() == 0) {
            throw new TaskOutOfRangeException(botStaticMemoryUnit.ERROR_MESSAGE_TASK_OUT_OF_RANGE);
        }
    }

}
