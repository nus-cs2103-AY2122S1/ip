import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Parser {

    /**
     * Returns the task number (as indexed in the task list)
     * of the task to be deleted or marked as done.
     *
     * @param keyword indicates if the task is to be deleted or marked as done.
     * @param task task to be deleted or marked as done (required).
     * @return index of the required task in the task list.
     */
    private static int getTaskNumber(String keyword, String task) {
        int idx = 0;
        for (int i = task.length() - 1; i > keyword.length(); i--) {
            idx += (task.charAt(i) - 48) * Math.pow(10, task.length() - 1 - i);
        }
        return idx - 1;
    }

    /**
     * Returns the index of the task to be deleted or marked as done
     * in case it is valid, else throws a BlitzException.
     *
     * @param command command entered by the user.
     * @param listSize current size of the task list.
     * @return index of the required task in the task list if valid.
     */
    public static int getIndex(String command, int listSize) throws BlitzException {
        String keyword = command.substring(0, command.indexOf(' '));
        int index = getTaskNumber(keyword, command);
        if (index < 0 || index >= listSize) {
            throw new BlitzException("You are attempting to "
                    + (keyword.equals("done") ? "mark" : "delete")
                    + " an invalid task number!");
        }
        return index;
    }

    /**
     * Makes sense of the user command and performs the
     * required action.
     *
     * @param command command entered by the user.
     * @param tasks current list of tasks
     *
     */
    public static String parse(String command, TaskList tasks) {
        String[] keywords = command.split(" ");

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

            //when keyword is not followed by anything
            if (isTask && command.length() < firstKeyword.length() + 2) {
                return new BlitzException("The description of a " + firstKeyword
                        + " cannot be empty.").toString();
            }

            if (isFeature) {
                switch (firstKeyword) {
                case "list":
                    if (tasks.size() == 0) {
                        result = new BlitzException("No items added to list yet!").toString();
                    } else {
                        result = tasks.listToString("Here are the tasks in your list:");
                    }

                    break;
                case "done":
                    try {
                        int index = getIndex(command, tasks.size());
                        Task finished = tasks.get(index);
                        finished.markAsDone();
                        result = "Nice! I've marked this task as done:\n" + "\t"
                                + finished;
                    } catch (BlitzException ex) {
                        return ex.toString();
                    }
                    break;
                case "delete":
                    try {
                        int index = Parser.getIndex(command, tasks.size());
                        Task deleted = tasks.deleteTask(index);
                        result = "Noted. I've removed this task:" + "\n\t" + deleted
                                + "\n\nNow you have " + tasks.size() + " tasks in the list.";
                    } catch (BlitzException ex) {
                        return ex.toString();
                    }
                    break;
                default:
                    String findKeyword = keywords[1];

                    if (tasks.size() == 0) {
                        return new BlitzException("Cannot perform find on empty list!!").toString();
                    } else {
                        TaskList matchingList = tasks.findMatchingTasks(findKeyword);
                        if (matchingList.size() == 0) {
                            return new BlitzException("No matches found").toString();
                        } else {
                            return matchingList.listToString("Here are the matching tasks in your list:");
                        }
                    }
                }

            } else {
                Task current = new Task("");
                switch (firstKeyword) {
                case "todo":
                    current = new Todo(command.substring(5));
                    break;
                case "deadline":
                    String date = command.substring(command.indexOf('/') + 4);
                    try {
                        LocalDateTime d = LocalDateTime.parse(date,
                                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                        current = new Deadline(command.substring(9,
                                command.indexOf('/') - 1), d);
                    } catch (DateTimeParseException e) {
                        return new BlitzException("Incorrect date/time format! Please enter "
                                + "deadline date/time in \"d M yyyy HHmm\" format").toString();
                    }

                    break;
                default:
                    String eventDate = command.substring(command.indexOf('/') + 4);
                    try {
                        LocalDateTime ed = LocalDateTime.parse(eventDate,
                                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                        current = new Event(command.substring(6, command.indexOf('/') - 1), ed);
                    } catch (DateTimeParseException e) {
                        return new BlitzException("Incorrect date/time format! Please enter "
                                + "event date/time in \"d M yyyy HHmm\" format").toString();
                    }
                    break;
                }

                tasks.addTask(current);
                result = "Got it. I've added this task:" + "\n\t" + current
                        + "\n\nNow you have " + tasks.size() + " tasks in the list.";
            }
        } else {
            //if the keyword is not valid
            result = new BlitzException("Sorry, but I don't know what that means :-(").toString();
        }
        return result;
    }
}
