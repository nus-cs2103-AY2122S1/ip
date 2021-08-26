package bot.assembly.function;

import bot.assembly.memory.BotStaticMemoryUnit;
import bot.assembly.memory.BotDynamicMemoryUnit;
import bot.assembly.memory.CommandInput;
import bot.assembly.error.InvalidCommandException;
import bot.assembly.error.InvalidCommandFormatException;
import bot.assembly.error.TaskOutOfRangeException;
import bot.assembly.error.InvalidTaskIndexException;

import bot.assembly.task.Task;
import bot.assembly.task.Deadline;
import bot.assembly.task.Event;
import bot.assembly.task.ToDo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class that handles the command input of the user
 */
public class BotCommandResponderUnit {

    private BotPrinter botPrinter = new BotPrinter();
    private BotStaticMemoryUnit botStaticMemoryUnit = new BotStaticMemoryUnit();
    private BotTemporalUnit botTemporalUnit = new BotTemporalUnit();
    private BotDynamicMemoryUnit botDynamicMemoryUnit = BotDynamicMemoryUnit.getInstance();

    /**
     * ArrayList as a main data structure to store Task
     */
    private List<Task> taskTracker = botDynamicMemoryUnit.taskTracker;

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
                    throw new InvalidCommandFormatException(
                            botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT
                    );
                }

                taskTracker.add(
                        new ToDo(inputToken[1])
                );

                break;

            case DEADLINE:

                String[] deadlineTask = inputToken[1].split(" /by ", 2);

                // throw InvalidCommandFormatException if command input does not contain task title and task datetime
                if (deadlineTask.length != 2) {
                    throw new InvalidCommandFormatException(
                            botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT
                    );
                }

                taskTracker.add(
                        new Deadline(
                                deadlineTask[0].trim(),
                                botTemporalUnit.convertStringToTemporalData(deadlineTask[1].trim())
                        )
                );

                break;

            case EVENT:

                String[] eventTask = inputToken[1].split(" /at ", 2);

                // throw InvalidCommandFormatException if command input does not contain task title and task datetime
                if (eventTask.length != 2) {
                    throw new InvalidCommandFormatException(
                            botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT
                    );
                }

                taskTracker.add(
                        new Event(
                                eventTask[0].trim(),
                                botTemporalUnit.convertStringToTemporalData(eventTask[1].trim())
                        )
                );

                break;

            default:
                //  throw InvalidCommandException if all 3 cases above are not triggered
                throw new InvalidCommandException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     * A method to check if the index entered by the user are truly an Integer:
     * This method is specifically used by command type of:
     * 1) Done
     * 2) Delete
     * @param str index of command input
     * @return true if command input's index can be converted to Integer
     */
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
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
    private void checkTaskListModificationCommand(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {

        // throw InvalidTaskIndexException if index entered is not an Integer
        if (!isInteger(input.split(" ", 2)[1])) {
            throw new InvalidTaskIndexException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_TASK_INDEX);
        }

        Integer index = Integer.parseInt(input.split(" ", 2)[1]);

        // throw TaskOutOfRangeException if the index entered is out of task list's range or when the task list is empty
        if (index - 1 > taskTracker.size() || taskTracker.size() == 0) {
            throw new TaskOutOfRangeException(botStaticMemoryUnit.ERROR_MESSAGE_TASK_OUT_OF_RANGE);
        }

    }

    /**
     * A method to check off a task and mark it as done
     * Then proceed to report feedback to user
     * @param input command input
     * @throws TaskOutOfRangeException if index entered is out of the range of the task list
     * @throws InvalidTaskIndexException if the command input's index entered cannot be converted to Integer
     */
    public void markTaskAsDone(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {

        // verify the command entered
        checkTaskListModificationCommand(input);

        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task completedTask = taskTracker.get(index-1);
        completedTask.markAsDone();

        String output = String.format(
                "%s\n\t\t%s%s\n\t%s",
                botStaticMemoryUnit.MESSAGE_TASK_COMPLETE,
                (index) + ". ",
                completedTask,
                botStaticMemoryUnit.MESSAGE_CHEERING);

        botPrinter.print(output);
    }

    /**
     * A method to delete a task from the list
     * and provide feedback to notify the users
     * @param input command input
     * @throws TaskOutOfRangeException if index entered is out of the range of the task list
     * @throws InvalidTaskIndexException if the command input's index entered cannot be converted to Integer
     */
    public void deleteTaskFromList(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {

        // verify the command entered
        checkTaskListModificationCommand(input);

        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task removedTask = taskTracker.get(index-1);

        String output = String.format(
                "%s\n\t\t%s%s\n\t",
                botStaticMemoryUnit.MESSAGE_REMOVE_TASK,
                (index) + ". ",
                removedTask);
        output += String.format(botStaticMemoryUnit.MESSAGE_ADD_TASK_SUMMARY, taskTracker.size() - 1);

        taskTracker.remove(removedTask);

        botPrinter.print(output);
    }

    // input: find fun
    private void checkFindTaskFormat(String input) throws InvalidCommandFormatException {
        String[] inputToken = tokenize(input);

        if (inputToken.length == 1) {
            throw new InvalidCommandFormatException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    // input: find fun joy happy
    public void findTaskFromList(String input) throws InvalidCommandFormatException{

        // 1. check the input format -> whether is correct or not (Done)
        // 2. parsing of data in to token (Done)
        // 3. create data structure to temporary store the String data (Done)
        // 4. Iterate through each token, check by keyword (Done)
        //      i) check if the DS already contains the task, yes -> add
        //      ii) else skip
        // 5. Check if the DS is empty
        // 6. If empty -> print some feedback
        //      i) create feedback message in static memory
        // 7. Not empty -> print proper message
        //      i) create opening message in static memory
        //      ii) format way to print
        //      iii) create summary message in static memory
        checkFindTaskFormat(input);

        // fun joy happy
        String keyword = tokenize(input)[1];
        // [fun, joy, happy]
        String[] keywordToken = keyword.split(" ");

        List<String> taskToStringList = new ArrayList<String>();
        List<String> searchResultList = new ArrayList<String>();
        botDynamicMemoryUnit.taskTracker.stream().forEach(x -> taskToStringList.add(x.toString()));

        for (String eachKeyword : keywordToken) {
            for (String eachTaskString : taskToStringList) {
                if (eachTaskString.contains(eachKeyword) && !searchResultList.contains(eachTaskString)) {
                    searchResultList.add("\t\t" + eachTaskString + "\n");
                }
            }
        }

        String keywordOutput = "";
        for (String eachKeyword : keywordToken) {
            keywordOutput += "\t\t" + eachKeyword + "\n";
        }

        if (searchResultList.isEmpty()) {

            botPrinter.print(botStaticMemoryUnit.MESSAGE_KEYWORD_NO_FOUND + keywordOutput);

        } else {
            
            String taskFoundOutput = "";
            for (String searchResult : searchResultList) {
                taskFoundOutput += searchResult;
            }

            botPrinter.print(String.format(
                    botStaticMemoryUnit.MESSAGE_TASK_FOUND,
                    keywordOutput,
                    taskFoundOutput
                    )
            );
        }

    }

    /**
     * A method to identify the purpose of the command
     * and convert the purpose of the command from string to enum
     * @param command
     * @return enum CommandInput
     * @throws InvalidCommandException if the command entered is not in CommandInput
     */
    public CommandInput identifyCommand(String command) throws InvalidCommandException{

        String commandInitial = command.trim().split(" ")[0];

        try {
            CommandInput taskType = CommandInput.valueOf(commandInitial.toUpperCase());
            return taskType;
        } catch (Exception e) {
            throw new InvalidCommandException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND);
        }
    }

}
