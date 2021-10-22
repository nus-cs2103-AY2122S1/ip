package duke.util;

import duke.exceptions.IllegalFormatException;
import duke.exceptions.UnknownCommandException;
import duke.task.TaskList;

/**
 * Encapsulates Parser of Duke bot.
 */
public class Parser {
    /**
     * Parses user input command and returns command to run in duke bot.
     *
     * @param command user input command
     * @return Command to run in duke bot.
     * @throws UnknownCommandException invalid user command
     */
    public static Command parse(String command) throws IllegalFormatException, UnknownCommandException {
        if (command.contains("help")) {
            checkCommandFormat("help", command);
            return taskList -> showHelp();
        } else if (command.contains("list")) {
            checkCommandFormat("list", command);
            return TaskList::printFullTaskList;
        } else if (command.contains("todo")) {
            checkCommandFormat("todo", command);
            return taskList -> taskList.addTodo(command);
        } else if (command.contains("event")) {
            checkCommandFormat("event", command);
            return taskList -> taskList.addEvent(command);
        } else if (command.contains("deadline")) {
            checkCommandFormat("deadline", command);
            return taskList -> taskList.addDeadline(command);
        } else if (command.contains("delete")) {
            checkCommandFormat("deleteTask", command);
            return taskList -> taskList.deleteTask(command);
        } else if (command.contains("done")) {
            checkCommandFormat("markTaskDone", command);
            return taskList -> taskList.markTaskDone(command);
        } else if (command.contains("find")) {
            checkCommandFormat("findTask", command);
            return taskList -> taskList.findFromList(command);
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Checks user command format.
     *
     * @param typeOfCommand command format to check for
     * @param command user input command to check
     * @throws IllegalFormatException if user gives invalid command
     */
    private static void checkCommandFormat(String typeOfCommand, String command)
            throws IllegalFormatException, UnknownCommandException {
        String helpRegexToMatch = "^help";
        String helpCorrectFormat = "help";
        String listRegexToMatch = "^list";
        String listCorrectFormat = "list";
        String toDoRegexToMatch = "^todo .*";
        String toDoCorrectFormat = "todo <todo description>";
        String eventRegexToMatch = "^event .* /at \\d{2}/\\d{2}/\\d{2} \\d{4}-\\d{4}";
        String eventCorrectFormat = "event <event description> /at <dd/MM/yy> <HHmm>-<HHmm>";
        String deadlineRegexToMatch = "^deadline .* /by \\d{2}/\\d{2}/\\d{2} \\d{4}";
        String deadlineCorrectFormat = "deadline <deadline description> /by <dd/MM/yy> <HHmm>";
        String markTaskDoneRegexToMatch = "^done [0-9].*";
        String markTaskDoneCorrectFormat = "done <task index>";
        String deleteTaskRegexToMatch = "^delete [0-9].*";
        String deleteTaskCorrectFormat = "delete <task index>";
        String findTaskRegexToMatch = "^find .*";
        String findTaskCorrectFormat = "find <keyword to find>";

        switch (typeOfCommand) {
        case "help":
            checkCommandFormat(command, helpRegexToMatch, helpCorrectFormat);
            break;
        case "list":
            checkCommandFormat(command, listRegexToMatch, listCorrectFormat);
            break;
        case "todo":
            checkCommandFormat(command, toDoRegexToMatch, toDoCorrectFormat);
            break;
        case "event":
            checkCommandFormat(command, eventRegexToMatch, eventCorrectFormat);
            break;
        case "deadline":
            checkCommandFormat(command, deadlineRegexToMatch, deadlineCorrectFormat);
            break;
        case "markTaskDone":
            checkCommandFormat(command, markTaskDoneRegexToMatch, markTaskDoneCorrectFormat);
            break;
        case "deleteTask":
            checkCommandFormat(command, deleteTaskRegexToMatch, deleteTaskCorrectFormat);
            break;
        case "findTask":
            checkCommandFormat(command, findTaskRegexToMatch, findTaskCorrectFormat);
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Checks user command format.
     *
     * @param command user input command
     * @param regex command format to match
     * @param correctFormat command format to follow
     * @throws IllegalFormatException if user gives invalid command
     */
    private static void checkCommandFormat(String command, String regex, String correctFormat)
            throws IllegalFormatException {
        if (!command.matches(regex)) {
            throw new IllegalFormatException(correctFormat);
        }
    }

    /**
     * Shows help page to guide user in usage of Duke bot.
     *
     * @return string representation of help page.
     */
    public static String showHelp() {
        return "Add Todo:\ntodo <todo description>\n\n"
                + "Add Event:\nevent <event description> /at <dd/MM/yy> <HHmm>-<HHmm>\n\n"
                + "Add Deadline:\ndeadline <deadline description> /by <dd/MM/yy> <HHmm>\n\n"
                + "Mark task as done:\ndone <task index>\n\n"
                + "Delete task:\ndelete <task index>\n\n"
                + "Show all tasks:\nlist\n\n"
                + "Find task(s):\nfind <keyword to find>\n\n"
                + "Quit application:\nbye";
    }
}
