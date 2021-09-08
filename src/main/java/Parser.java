import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class Parser {

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

    /**
     * Makes sense of the user command and performs the
     * required action.
     *
     * @param command command entered by the user.
     * @param tasks current list of tasks
     *
     */

    public static String parseCommand (String command, TaskList tasks, Ui ui) {
        String[] keywords = command.split(" ");
        assert(keywords.length > 0);
        //stores the first word (keyword) in the user input
        String firstKeyword = keywords[0];

        //stores whether keyword is a task or calling a feature
        boolean isTask = firstKeyword.equals("todo") || firstKeyword.equals("event")
                || firstKeyword.equals("deadline");
        boolean isFeature = firstKeyword.equals("list") || firstKeyword.equals("done")
                || firstKeyword.equals("delete") || firstKeyword.equals("find");

        //stores the string representation of parse result
        String result = "";
        /*If the keyword is a task or a feature other than "list"
        then it cannot be the only word in the command */
        if ((isTask || (isFeature && !firstKeyword.equals("list"))) && keywords.length < 2) {
            return isTask ? ui.getTaskDescriptionCannotBeEmptyMessage()
                          : ui.getInvalidCommandFormatMessage();
        }
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
            case "todo" :
                String todoTask = keywords[1];
                result = tasks.addTask(new Todo(todoTask));
                break;
            case "deadline":
                String deadlineTask = command.substring(9, command.indexOf('/') - 1);
                String deadlineDateTime = command.substring(command.indexOf('/') + 4);
                result = tasks.addTask(new Deadline(deadlineTask, parseDateTime(deadlineDateTime)));
                break;
            case "event":
                String eventName = command.substring(6, command.indexOf('/') - 1);
                String eventDateTime = command.substring(command.indexOf('/') + 4);
                result = tasks.addTask(new Event(eventName, parseDateTime(eventDateTime)));
                break;
            default:
                result = ui.getCommandNotFoundMessage();
            }
        } catch (BlitzException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            return ui.getIncorrectDateTimeFormatMessage();
        } catch (Exception e) {
            return ui.getInvalidCommandFormatMessage();
        }
        return result;
    }
}
