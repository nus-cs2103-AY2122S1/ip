package blitz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import blitz.tasks.Deadline;
import blitz.tasks.Event;
import blitz.tasks.Todo;

public class Parser {

    private static boolean isIncompleteTaskDescription = false;

    /**
     * Parses string in d/M/YYYY HHmm format.
     *
     * @param dateTime string containing date and time to be parsed.
     * @return LocalDateTime object that represents the provided string.
     * @throws DateTimeParseException when dateTime isn't of proper format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        return LocalDateTime.parse(dateTime,
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    private static void checkIncompleteTaskDescription(String command, String[] keywords) {
        String firstKeyword = keywords[0];
        if (keywords.length >= 2) {
            switch (firstKeyword) {
            case "todo":
                isIncompleteTaskDescription = keywords.length < 2;
                break;
            case "deadline":
                String deadlineName = command.substring(9, command.indexOf('/'));
                String deadlineDateTime = command.substring(command.indexOf('/'));
                isIncompleteTaskDescription = deadlineName.isEmpty()
                        || deadlineDateTime.split(" ").length < 3;
                break;
            case "event":
                String eventName = command.substring(6, command.indexOf('/'));
                String eventDateTime = command.substring(command.indexOf('/') );
                isIncompleteTaskDescription = eventName.isEmpty()
                        || eventDateTime.split(" ").length < 3;
                break;
            default:
            }
        } else {
            isIncompleteTaskDescription = true;
        }
    }

    private static String parseTask(String command, String firstKeyword, TaskList tasks, Ui ui) {
        String result = "";
        try {
            switch (firstKeyword) {
            case "todo":
                String todoTask = command.substring(5);
                result = tasks.addTask(new Todo(todoTask));
                break;
            case "deadline":
                String deadlineTask = command.substring(9, command.indexOf('/') - 1);
                String deadlineDateTime = command.substring(command.indexOf('/') + 4);
                result = tasks.addTask(new Deadline(deadlineTask,
                        parseDateTime(deadlineDateTime)));
                break;
            case "event":
                String eventName = command.substring(6, command.indexOf('/') - 1);
                String eventDateTime = command.substring(command.indexOf('/') + 4);
                result = tasks.addTask(new Event(eventName, parseDateTime(eventDateTime)));
                break;
            default:
            }
        } catch (BlitzException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            return ui.getIncorrectDateTimeFormatMessage() + ui.getHelpPrompt();
        }
        return result;
    }

    private static String parseFeature(String[] keywords, TaskList tasks, Ui ui) {
        String firstKeyword = keywords[0];
        String result = "";
        try {
            switch (firstKeyword) {
            case "list":
                result = tasks.listToString(ui.getListMessage(), ui);
                break;
            case "done":
                int taskToMarkDone = Integer.valueOf(keywords[1]);
                result = tasks.markTaskAsDone(taskToMarkDone - 1);
                break;
            case "delete":
                int taskToDelete = Integer.valueOf(keywords[1]);
                result = tasks.deleteTask(taskToDelete - 1);
                break;
            case "find":
                String findKeyword = keywords[1];
                TaskList matchingList = tasks.findMatchingTasks(findKeyword);
                result = matchingList.listToString(ui.getMatchingListMessage(), ui);
                break;
            case "help":
                result = ui.help(keywords[1]);
                break;
            default:
            }
        } catch (BlitzException e) {
            return e.toString();
        } catch (Exception e) {
            return ui.getInvalidCommandFormatMessage() + ui.getHelpPrompt();
        }
        return result;
    }
    /**
     * Makes sense of the user command and performs the
     * required action.
     *
     * @param command command entered by the user.
     * @param tasks current list of blitz.tasks
     *
     */
    public static String parseCommand(String command, TaskList tasks, Ui ui) {
        String[] keywords = command.split(" ");
        assert(keywords.length > 0);
        String firstKeyword = keywords[0];
        boolean isTask = firstKeyword.equals("todo") || firstKeyword.equals("event")
                || firstKeyword.equals("deadline");
        boolean isFeature = firstKeyword.equals("list") || firstKeyword.equals("done")
                || firstKeyword.equals("delete") || firstKeyword.equals("find") || firstKeyword.equals("help");
        if (isTask) {
            if (!firstKeyword.equals("todo") && !command.contains("/")) {
                return ui.getInvalidCommandFormatMessage() + ui.getHelpPrompt();
            }
            checkIncompleteTaskDescription(command, keywords);
            if (isIncompleteTaskDescription) {
                return ui.getTaskDescriptionCannotBeIncompleteMessage() + ui.getHelpPrompt();
            }
            return Parser.parseTask(command, firstKeyword, tasks, ui);
        } else if (isFeature) {
            if (!firstKeyword.equals("list") && keywords.length < 2) {
                return ui.getIncompleteCommandMessage() + ui.getHelpPrompt();
            }
            return Parser.parseFeature(keywords, tasks, ui);
        } else {
            return ui.getCommandNotFoundMessage();
        }
    }
}
