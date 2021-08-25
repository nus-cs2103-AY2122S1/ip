import java.util.List;

public class BotCommandResponderUnit {

    BotPrinter botPrinter = new BotPrinter();
    BotStaticMemoryUnit botStaticMemoryUnit = new BotStaticMemoryUnit();
    BotTemporalUnit botTemporalUnit = new BotTemporalUnit();
    BotDynamicMemoryUnit botDynamicMemoryUnit = BotDynamicMemoryUnit.getInstance();

    List<Task> taskTracker = botDynamicMemoryUnit.taskTracker;

    public BotCommandResponderUnit() {}

    /**
     * Tokenizes input to 2 parts:
     * 1. Command Type
     * 2. Rest of the command
     * @param input
     * @return [Command Type, Rest of the command]
     */
    public String[] tokenize(String input) {

        String[] token = input.split(" ", 2);

        return token;
    }

    /**
     * Check the type of command, whether we can recognise
     * if can, then add the newly instantiated task into the task tracker
     * @param input
     * @throws InvalidCommandException for any commands that cannot be recognised
     * @throws InvalidCommandFormatException for wrong format of command
     */
    public void addTask(String input) throws InvalidCommandException, InvalidCommandFormatException{

        String[] inputToken = tokenize(input);
        CommandInput taskType = identifyCommand(input);

        switch (taskType) {

            case TODO:

                //  throw invalid format exception if "todo" is entered without anymore information
                if (inputToken.length == 1) {
                    throw new InvalidCommandFormatException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
                }

                taskTracker.add(
                        new ToDo(inputToken[1])
                );

                break;

            case DEADLINE:

                String[] deadlineTask = inputToken[1].split(" /by ", 2);

                // throw invalid format exception if deadline is not in the format of "task /by time"
                if (deadlineTask.length != 2) {
                    throw new InvalidCommandFormatException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
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

                // throw invalid command format if event is not in the format of "task /at time"
                if (eventTask.length != 2) {
                    throw new InvalidCommandFormatException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
                }

                taskTracker.add(
                        new Event(
                                eventTask[0].trim(),
                                botTemporalUnit.convertStringToTemporalData(eventTask[1].trim())
                        )
                );

                break;

            default:
                throw new InvalidCommandException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     * A method to check if the index entered by the user are truly an Integer:
     * 1. done
     * 2. delete
     * @param str
     * @return
     */
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * A method to check the validity of the index entered for:
     * 1. mark task as done
     * 2. delete task from list
     * @param input
     * @throws TaskOutOfRangeException
     * @throws InvalidTaskIndexException
     */
    private void checkTaskListModificationCommand(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {

        // throw invalid index exception if index entered is not an Integer
        if (!isInteger(input.split(" ", 2)[1])) {
            throw new InvalidTaskIndexException(botStaticMemoryUnit.ERROR_MESSAGE_INVALID_TASK_INDEX);
        }

        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        //  if the list is empty
        if (index - 1 > taskTracker.size() || taskTracker.size() == 0) {
            throw new TaskOutOfRangeException(botStaticMemoryUnit.ERROR_MESSAGE_TASK_OUT_OF_RANGE);
        }

    }

    /**
     * A method to mark the task's isDone to be true
     * and provide some feedback to notify the users
     * @param input
     * @throws TaskOutOfRangeException
     * @throws InvalidTaskIndexException
     */
    public void markTaskAsDone(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {

        checkTaskListModificationCommand(input);

        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task completedTask = taskTracker.get(index-1);
        completedTask.maskAsDone();

        String output = String.format("%s\n\t\t%s%s\n\t%s",
                botStaticMemoryUnit.MESSAGE_TASK_COMPLETE,
                (index) + ". ",
                completedTask,
                botStaticMemoryUnit.MESSAGE_CHEERING);

        botPrinter.print(output);
    }

    /**
     * A method to delete a task from the list
     * and provide some feedback to notify the users
     * @param input
     * @throws TaskOutOfRangeException
     * @throws InvalidTaskIndexException
     */
    public void deleteTaskFromList(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {

        checkTaskListModificationCommand(input);

        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task removedTask = taskTracker.get(index-1);

        String output = String.format("%s\n\t\t%s%s\n\t",
                botStaticMemoryUnit.MESSAGE_REMOVE_TASK,
                (index) + ". ",
                removedTask);

        output += String.format(botStaticMemoryUnit.MESSAGE_ADD_TASK_SUMMARY, taskTracker.size() - 1);

        taskTracker.remove(removedTask);

        botPrinter.print(output);
    }

    /**
     * A method to identify the command types
     * and Convert from String to Enum
     * @param command
     * @return enum CommandInput
     * @throws InvalidCommandException
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
