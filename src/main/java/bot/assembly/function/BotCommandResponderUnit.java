package bot.assembly.function;

import java.util.ArrayList;
import java.util.List;

import bot.assembly.error.InvalidCommandException;
import bot.assembly.error.InvalidCommandFormatException;
import bot.assembly.error.InvalidTaskIndexException;
import bot.assembly.error.TaskOutOfRangeException;
import bot.assembly.memory.BotDynamicMemoryUnit;
import bot.assembly.memory.BotStaticMemoryUnit;
import bot.assembly.memory.CommandInput;
import bot.assembly.task.Deadline;
import bot.assembly.task.Event;
import bot.assembly.task.Task;
import bot.assembly.task.ToDo;

/**
 * A class that handles the command input of the user
 */
public class BotCommandResponderUnit {

    private BotStaticMemoryUnit botStaticMemoryUnit = new BotStaticMemoryUnit();
    private BotTemporalUnit botTemporalUnit = new BotTemporalUnit();
    private BotDynamicMemoryUnit botDynamicMemoryUnit = BotDynamicMemoryUnit.getInstance();
    private BotMassOps botMassOps = new BotMassOps();
    private BotCommandChecker botCommandChecker = new BotCommandChecker();

    /**
     * ArrayList as a main data structure to store Task
     */
    private List<Task> taskTracker = botDynamicMemoryUnit.getTaskTacker();

    /**
     * Constructor
     */
    public BotCommandResponderUnit() {}

    /**
     * A method that tokenizes the command input to 2 parts:
     * 1. Command Type
     * 2. Rest of the command
     * @param input command input
     * @return [Command Type, Rest of the command]
     */
    private String[] tokenize(String input) {

        String[] token = input.split(" ", 2);

        return token;
    }

    /**
     * A method to check off a task and mark it as done
     * Then proceed to report feedback to user
     * @param input command input
     * @throws TaskOutOfRangeException if index entered is out of the range of the task list
     * @throws InvalidTaskIndexException if the command input's index entered cannot be converted to Integer
     */
    public String markTaskAsDone(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {

        // verify the command entered
        botCommandChecker.checkTaskListModificationCommand(input);

        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task completedTask = taskTracker.get(index - 1);
        completedTask.markAsDone();

        String output = String.format(
                "%s\n%s%s\n%s",
                botStaticMemoryUnit.MESSAGE_TASK_COMPLETE, (index) + ". ",
                completedTask,
                botStaticMemoryUnit.MESSAGE_CHEERING);

        return output;
    }

    /**
     * A method to delete a task from the list
     * and provide feedback to notify the users
     * @param input command input
     * @throws TaskOutOfRangeException if index entered is out of the range of the task list
     * @throws InvalidTaskIndexException if the command input's index entered cannot be converted to Integer
     */
    public String deleteTaskFromList(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {

        // verify the command entered
        botCommandChecker.checkTaskListModificationCommand(input);

        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task removedTask = taskTracker.get(index - 1);

        String output = String.format(
                "%s\n%s%s\n",
                botStaticMemoryUnit.MESSAGE_REMOVE_TASK, (index) + ". ",
                removedTask);
        output += String.format(botStaticMemoryUnit.MESSAGE_ADD_TASK_SUMMARY, taskTracker.size() - 1);

        taskTracker.remove(removedTask);

        return output;
    }

    /**
     * A method to identify the purpose of the command
     * and convert the purpose of the command from string to enum
     * @param command
     * @return enum CommandInput
     * @throws InvalidCommandException if the command entered is not in CommandInput
     */
    public CommandInput identifyCommand(String command) throws InvalidCommandException {

        String commandInitial = command.trim().split(" ")[0];

        try {
            CommandInput taskType = CommandInput.valueOf(commandInitial.toUpperCase());
            return taskType;
        } catch (Exception e) {
            throw new InvalidCommandException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     *
     * @param input
     * @return
     * @throws InvalidCommandException
     */
    public void massOps(String input) throws InvalidCommandException {
        CommandInput commandType = identifyCommand(tokenize(input)[1]);

        switch (commandType) {
        case DELETE:
            botMassOps.deleteAll(taskTracker);
            break;

        case DONE:
            botMassOps.doneAll(taskTracker);
            break;

        default:
            throw new InvalidCommandException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * A method that checks the type of command and proceed to break down the command into
     * 1) Task Title
     * 2) Task DateTime (format: ISO_LOCAL_DATE_TIME)
     *
     * Then proceed to create the respective task and add into the task list
     * @param input command input
     * @throws InvalidCommandException if the command input entered is not TODO, EVENT, DEADLINE
     * @throws InvalidCommandFormatException if the command input entered is in the wrong format
     */
    public void addTask(String input) throws InvalidCommandException, InvalidCommandFormatException {

        String[] inputToken = tokenize(input);
        CommandInput taskType = identifyCommand(input);

        switch (taskType) {
        case TODO:
            //  throw InvalidCommandFormatException if command input is entered without task title
            if (inputToken.length == 1) {
                throw new InvalidCommandFormatException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
            }

            taskTracker.add(new ToDo(inputToken[1]));
            break;

        case DEADLINE:
            String[] deadlineTask = inputToken[1].split(" /by ", 2);
            // throw InvalidCommandFormatException if command input does not contain task title and task datetime
            if (deadlineTask.length != 2) {
                throw new InvalidCommandFormatException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
            }

            taskTracker.add(new Deadline(
                            deadlineTask[0].trim(),
                            botTemporalUnit.convertStringToTemporalData(deadlineTask[1].trim())
                    )
            );
            break;

        case EVENT:

            String[] eventTask = inputToken[1].split(" /at ", 2);
            // throw InvalidCommandFormatException if command input does not contain task title and task datetime
            if (eventTask.length != 2) {
                throw new InvalidCommandFormatException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
            }

            taskTracker.add(new Event(
                            eventTask[0].trim(),
                            botTemporalUnit.convertStringToTemporalData(eventTask[1].trim())
                    )
            );
            break;

        default:
            //  throw InvalidCommandException if all 3 cases above are not triggered
            assert false : "addTask() Method Error, TODO, DEADLINE, EVENT command parsed wrongly.";
            throw new InvalidCommandException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     * A method that takes in string input, parses them into tokens as keywords
     * Then proceed to iterate through the task list to filter the respective task that contains the keywords
     * @param input keyword in a continuous string format
     * @throws InvalidCommandFormatException if checkFindTaskFormat() fails
     */
    public String findTaskFromList(String input) throws InvalidCommandFormatException {

        botCommandChecker.checkFindTaskFormat(input);
        String keyword = tokenize(input)[1];
        String[] keywordToken = keyword.split(" ");

        List<String> taskToStringList = new ArrayList<String>();
        List<String> searchResultList = new ArrayList<String>();

        taskTracker.stream().forEach(x -> taskToStringList.add(x.toString()));

        for (String eachKeyword : keywordToken) {
            for (String eachTaskString : taskToStringList) {
                if (eachTaskString.contains(eachKeyword) && !searchResultList.contains(eachTaskString)) {
                    searchResultList.add("\t" + eachTaskString + "\n");
                }
            }
        }

        String keywordOutput = "";
        for (String eachKeyword : keywordToken) {
            keywordOutput += "\t" + eachKeyword + "\n";
        }

        if (searchResultList.isEmpty()) {

            return botStaticMemoryUnit.MESSAGE_KEYWORD_NO_FOUND + keywordOutput;

        } else {
            String taskFoundOutput = "";
            for (String searchResult : searchResultList) {
                taskFoundOutput += searchResult;
            }

            return String.format(
                    botStaticMemoryUnit.MESSAGE_TASK_FOUND,
                    keywordOutput,
                    taskFoundOutput
            );
        }

    }

}
