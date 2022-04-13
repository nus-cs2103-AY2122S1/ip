package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;

/**
 * The Parser class processes the data inputs from the Ui, and uses this input to alter the list
 */
public class Parser {

    /**
     * Checks the date input from the user to see if it is a valid date
     *
     * @param dateStr Date taken in to check for validity
     * @return If date is valid
     */
    public static boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter in the format: yyyy-mm-dd");
            return false;
        }
        return true;
    }

    public static boolean validIntInput(String string) {
        assert string.length() == 3 : "Invalid task input";
        if (!Character.isDigit(string.charAt(0))) {
            return false;
        }
        if (!Character.isDigit(string.charAt(1)) && !Character.isWhitespace(string.charAt(1))) {
            return false;
        }
        if (!Character.isWhitespace(string.charAt(2)) && string.charAt(2) != '/') {
            return false;
        }
        return true;
    }
    /**
     * The main method that processes the Ui input,
     * @param in The input from the Ui to be processed
     * @param tasklist The list that is adjusted when a specific input is given
     */
    public static String parse(String in, TaskList tasklist) {
        try {
            if (in.equals("bye") || in.equals("Bye")) {
                return Parser.byeOutput();
            } else if (in.equals("list") || in.equals("List")) {
                return Parser.listOutput(tasklist);
            } else if (in.substring(0, 4).equals("done")) {
                return Parser.doneOutput(in, tasklist);
            } else if (in.substring(0, 6).equals("delete")) {
                return Parser.deleteOutput(in, tasklist);
            } else if (in.substring(0, 4).equals("todo")) {
                return Parser.toDoOutput(in, tasklist);
            } else if (in.substring(0, 5).equals("event")) {
                return Parser.eventOutput(in, tasklist);
            } else if (in.substring(0, 8).equals("deadline")) {
                return Parser.deadlineOutput(in, tasklist);
            } else if (in.substring(0, 4).equals("find")) {
                return Parser.findOutput(in, tasklist);
            } else if (in.substring(0, 10).equals("reschedule")) {
                return Parser.rescheduleOutput(in, tasklist);
            } else {
                return Parser.invalidInputResponse();
            }
        } catch (StringIndexOutOfBoundsException e) {
            return Parser.invalidInputResponse();
        }
    }

    /**
     * The default message when the input is not a proper command
     */
    public static String invalidInputResponse() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Exits Duke and closes the scanner
     */
    public static String byeOutput() {
        return "Bye. Hope to see you again!";
    }

    /**
     * Lists out the items in the list
     * @param tasklist items to be listed
     */
    public static String listOutput(TaskList tasklist) {
        return tasklist.showList();
    }

    /**
     * Marks the given task as done
     * @param in task to be marked as done
     * @param taskList
     */
    public static String doneOutput(String in, TaskList taskList) throws NumberFormatException {
        assert in.length() > 5 : "Invalid Input for done command";
        try {
            int taskDone = parseInt(in.substring(5));
            if (taskDone > 100) {
                return "Invalid Input for done command. Task too big";
            } else {
                return taskList.doTask(taskDone);
            }
        } catch (NumberFormatException e) {
            return "No task number detected";
        }
    }

    /**
     * Deletes the given task
     * @param in task to be marked as deleted
     * @param taskList
     */
    public static String deleteOutput(String in, TaskList taskList) {
        assert in.length() < 7 : "Invalid Input for delete command";
        try {
            int taskDeleted = parseInt(in.substring(7));
            return taskList.deleteTask(taskDeleted);
        } catch (NumberFormatException e) {
            return "Invalid Input for delete command";
        }
    }

    /**
     * Adds the given input as a todo task to the list
     * @param in todo task to be added
     * @param taskList
     */
    public static String toDoOutput(String in, TaskList taskList) {
        assert in.length() != 4 : "OOPS!!! The description of a todo cannot be empty.";
        return taskList.addToDoTask(in);
        }

    /**
     * Adds the given input as a event task to the list
     * @param in event task to be added
     * @param taskList
     */
    public static String eventOutput(String in, TaskList taskList) {
        int i = in.indexOf("/");
        assert i >= 0 : "Time not detected. Please try again";
        try {
            if (isValid(in.substring(i + 1, i + 11))) {
                return taskList.addEventTask(in, i);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return "Invalid date and time. Use yyyy-mm-dd format for date";
        }
        return "Invalid date and time";
    }

    /**
     * Adds the given input as a deadline task to the list
     * @param in deadline task to be added
     * @param taskList
     */
    public static String deadlineOutput(String in, TaskList taskList) {
        int i = in.indexOf("/");
        assert i >= 0 : "Time not detected. Please try again";
            try {
                if (isValid(in.substring(i + 1, i + 11))) {
                    return taskList.addDeadlineTask(in, i);
                }
            } catch (StringIndexOutOfBoundsException e) {
                return "Invalid date and time";
            }
        return "Invalid date and time";
    }

    /**
     * Returns a list of all the tasks containing that keyword in the tasklist
     * @param in keyword to be found
     * @param tasklist
     */
    public static String findOutput(String in, TaskList tasklist) {
        String keyword = in.substring(4);
        return tasklist.findTask(keyword);
    }

    /**
     * Postpones the selected task to another date
     * @param in task to be postponed
     * @param tasklist
     */
    public static String rescheduleOutput(String in, TaskList tasklist) {
        assert in.length() > 9 : "Invalid Input for reschedule command";
        try {
            validIntInput(in.substring(11, 14));
        } catch (StringIndexOutOfBoundsException e) {
            return "Invalid Input for reschedule command check";
        }
        if (validIntInput(in.substring(11, 14))) {
            int i = in.indexOf("/");
            try {
                if (isValid(in.substring(i + 1, i + 11))) {
                    if (Character.isDigit(in.charAt(10))) {
                        return tasklist.rescheduleTask(parseInt(in.substring(11, 13)),
                                in.substring(i + 1, i + 11));
                    } else {
                        return tasklist.rescheduleTask(parseInt(in.substring(11, 12)),
                                in.substring(i + 1, i + 11));
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                return "Invalid date and time";
            } catch (NumberFormatException e) {
                return "Invalid task input";
            }
        }
        return "Invalid Input for reschedule command";
    }
}

