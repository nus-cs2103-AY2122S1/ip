import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Parser {

    /**
     * Parses string in d/M/YYYY HHmm format.
     *
     * @param dateTime string containing date and time to be parsed.
     * @return LocalDateTime object that represents the provided string.
     * @throws DateTimeParseException
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
    public static String parseCommand(String command, TaskList tasks, Ui ui) {
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

        //suppose it's a valid keyword
        if (isTask || isFeature) {
            if (isFeature) {
                switch (firstKeyword) {
                case "list":
                    try {
                        result = tasks.listToString("Here are the tasks in your list:", ui);
                    } catch (BlitzException ex) {
                        return ex.toString();
                    }
                    break;
                case "done":
                    try {
                        int taskToMarkDone = Integer.valueOf(keywords[1]);
                        result = tasks.markTaskAsDone(taskToMarkDone - 1);
                    } catch (BlitzException ex) {
                        return ex.toString();
                    } catch (Exception ex) {
                        return "Invalid format!! Enter : done <task number to mark as done>";
                    }
                    break;
                case "delete":
                    try {
                        int taskToDelete = Integer.valueOf(keywords[1]);
                        result = tasks.deleteTask(taskToDelete - 1);
                    } catch (BlitzException ex) {
                        return ex.toString();
                    } catch (Exception ex) {
                        return "Invalid format!! Enter : delete <task number to delete>";
                    }
                    break;
                default:
                    String findKeyword = keywords[1];
                    try {
                        TaskList matchingList = tasks.findMatchingTasks(findKeyword);
                        matchingList.listToString("Here are the matching tasks in your list:", ui);
                    } catch (BlitzException ex) {
                        return ex.toString();
                    }
                    break;
                }

            } else {
                assert(isTask);
                if (keywords.length < 2) {
                    return new BlitzException("The description of a " + firstKeyword
                            + " cannot be empty.").toString();
                }
                Task current = new Task("");
                String nameOfTask = keywords[1];
                switch (firstKeyword) {
                case "todo":
                    current = new Todo(nameOfTask);
                    break;
                case "deadline":
                    String deadlineDateTime = command.substring(command.indexOf('/') + 4);
                    current = new Deadline(nameOfTask, parseDateTime(deadlineDateTime));
                    try {
                        current = new Deadline(nameOfTask, parseDateTime(deadlineDateTime));
                    } catch (DateTimeParseException e) {
                        return "Incorrect date/time format! Please enter "
                                + "deadline date/time in \"d M yyyy HHmm\" format";
                    }
                    break;
                default:
                    String eventDateTime = command.substring(command.indexOf('/') + 4);
                    try {
                        current = new Event(nameOfTask, parseDateTime(eventDateTime));
                    } catch (DateTimeParseException e) {
                        return new BlitzException("Incorrect date/time format! Please enter "
                                + "event date/time in \"d M yyyy HHmm\" format").toString();
                    }
                    break;
                }
                result = tasks.addTask(current);
            }
        } else {
            //if the keyword is not valid
            assert(!(isFeature || isTask));
            result = new BlitzException("Sorry, but I don't know what that means :-(").toString();
        }
        return result;
    }
}
