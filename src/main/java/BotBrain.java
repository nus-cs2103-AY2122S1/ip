import java.util.List;
import java.util.Scanner;

public class BotBrain {

    private BotMemory botMemory = new BotMemory();
    private BotPrinter botPrinter = new BotPrinter();
    private List<Task> taskTracker = botMemory.taskTracker;

    /**
     * Constrcutor
     */
    BotBrain() {

    }

    /**
     * Tokenizes input to 2 parts:
     * 1. Command Type
     * 2. Rest of the command
     * @param input
     * @return [Command Type, Rest of the command]
     */
    private String[] tokenize(String input) {
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
    private void classifyTask(String input) throws InvalidCommandException, InvalidCommandFormatException{
        String[] inputToken = tokenize(input);
        String taskType = inputToken[0];

        switch (taskType){
            case "todo":
                //  throw invalid format exception if "todo" is entered without anymore information
                if (inputToken.length == 1) {
                    throw new InvalidCommandFormatException(botMemory.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
                }
                taskTracker.add(new ToDo(inputToken[1]));
                return;

            case "deadline":
                String[] deadlineTask = inputToken[1].split(" /by ", 2);

                // throw invalid format exception if deadline is not in the format of "task /by time"
                if (deadlineTask.length != 2) {
                    throw new InvalidCommandFormatException(botMemory.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
                }
                taskTracker.add(new Deadline(deadlineTask[0].trim(), deadlineTask[1].trim()));
                return;

            case "event":
                String[] eventTask = inputToken[1].split(" /at ", 2);

                // throw invalid command format if event is not in the format of "task /at time"
                if (eventTask.length != 2) {
                    throw new InvalidCommandFormatException(botMemory.ERROR_MESSAGE_INVALID_COMMAND_FORMAT);
                }
                taskTracker.add(new Event(eventTask[0].trim(), eventTask[1].trim()));
                return;

            default:
                throw new InvalidCommandException(botMemory.ERROR_MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     *  A method to feedback to user of the new task added
     */
    private void generateTaskFeedback(){
        String output = String.format(
                "%s\n\t\t%s\n\t",
                botMemory.MESSAGE_ADD_TASK_NOTICE,
                taskTracker.get(taskTracker.size()-1).toString());
        output+= String.format(
                botMemory.MESSAGE_ADD_TASK_SUMMARY,
                taskTracker.size());
        botPrinter.print(output);
    }

    /**
     * A method to format all tasks from the list into a single string for printing
     * @return (String) formatted list of tasks
     * @throws EmptyTaskListException for empty list
     */
    private String formatTaskTracker() throws EmptyTaskListException {
        // throw empty list exception if task list is empty
        if (taskTracker.size() == 0) {
            throw new EmptyTaskListException(botMemory.ERROR_MESSAGE_EMPTY_TASKLIST);
        }
        StringBuilder formattedTask = new StringBuilder();
        formattedTask.append(botMemory.MESSAGE_TASK_REPORT + "\n\t");
        taskTracker.stream().
                forEach(x -> formattedTask.append((taskTracker.indexOf(x) + 1) + ". " + x.toString() + "\n\t"));
        formattedTask.append("(end)");
        return formattedTask.toString();
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
            throw new InvalidTaskIndexException(botMemory.ERROR_MESSAGE_INVALID_TASK_INDEX);
        }
        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        //  if the list is empty
        if (index - 1 > taskTracker.size() || taskTracker.size() == 0) {
            throw new TaskOutOfRangeException(botMemory.ERROR_MESSAGE_TASK_OUT_OF_RANGE);
        }

    }

    /**
     * A method to mark the task's isDone to be true
     * and provide some feedback to notify the users
     * @param input
     * @throws TaskOutOfRangeException
     * @throws InvalidTaskIndexException
     */
    private void markTaskAsDone(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {
        checkTaskListModificationCommand(input);
        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task completedTask = taskTracker.get(index-1);
        completedTask.maskAsDone();
        String output = String.format("%s\n\t\t%s%s\n\t%s",
                botMemory.MESSAGE_TASK_COMPLETE,
                (index) + ". ",
                completedTask,
                botMemory.MESSAGE_CHEERING);
        botPrinter.print(output);
    }

    /**
     * A method to delete a task from the list
     * and provide some feedback to notify the users
     * @param input
     * @throws TaskOutOfRangeException
     * @throws InvalidTaskIndexException
     */
    private void deleteTaskFromList(String input) throws TaskOutOfRangeException, InvalidTaskIndexException {
        checkTaskListModificationCommand(input);
        Integer index = Integer.parseInt(input.split(" ", 2)[1]);
        Task removedTask = taskTracker.get(index-1);
        String output = String.format("%s\n\t\t%s%s\n\t",
                botMemory.MESSAGE_REMOVE_TASK,
                (index) + ". ",
                removedTask);
        output += String.format(botMemory.MESSAGE_ADD_TASK_SUMMARY, taskTracker.size() - 1);
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
    private CommandInput identifyCommand(String command) throws InvalidCommandException{
        String commandInitial = command.trim().split(" ")[0];
        CommandInput taskType;

        if (commandInitial.equals("bye")) {
            taskType = CommandInput.BYE;
        } else if (commandInitial.equals("list")) {
            taskType = CommandInput.LIST;
        } else if (commandInitial.equals("done")) {
            taskType = CommandInput.DONE;
        } else if (commandInitial.equals("delete")) {
            taskType = CommandInput.DELETE;
        } else if (commandInitial.equals("todo")) {
            taskType = CommandInput.TODO;
        } else if (commandInitial.equals("deadline")) {
            taskType = CommandInput.DEADLINE;
        } else if (commandInitial.equals("event")) {
            taskType = CommandInput.EVENT;
        } else {
            throw new InvalidCommandException(botMemory.ERROR_MESSAGE_INVALID_COMMAND);
        }

        return taskType;
    }

    /**
     * A method to read the user's input and respond to it
     */
    private void interact() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.nextLine().trim();
                CommandInput commandInitial = identifyCommand(input);

                switch (commandInitial) {
                    case BYE:
                        botPrinter.print(botMemory.MESSAGE_GOODBYE);
                        return;
                    case LIST:
                        botPrinter.print(formatTaskTracker());
                        break;
                    case DONE:
                        markTaskAsDone(input);
                        break;
                    case DELETE:
                        deleteTaskFromList(input);
                        break;
                    default:
                        classifyTask(input);
                        generateTaskFeedback();
                }
            }
            catch (Exception error){
                botPrinter.print(botMemory.ERROR_MESSAGE_PROMPT + error.getMessage());
            }
        }
    }

    /**
     * A method to initiate the bot's brain to interact with the user
     */
    public void initiate() {
        System.out.println("\t" + botMemory.LOGO.replaceAll("\n", "\n\t"));
        botPrinter.print(botMemory.MESSAGE_GREETING);
        this.interact();
    }
}
