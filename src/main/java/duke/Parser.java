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
            } else if (in.substring(0,4).equals("find")) {
                return Parser.findOutput(in, tasklist);
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
    public static String doneOutput(String in, TaskList taskList) {
        if (in.length() < 6) {
            return "Invalid Input for done command";
        } else {
            int taskDone = parseInt(in.substring(5));
            if (taskDone > 100) {
                return "Invalid Input for done command";
            } else {
                return taskList.doTask(taskDone);
            }
        }
    }

    /**
     * Deletes the given task
     * @param in task to be marked as deleted
     * @param taskList
     */
    public static String deleteOutput(String in, TaskList taskList) {
        if (in.length() < 8) {
            return "Invalid Input for delete command";
        } else {
            try {
                int taskDeleted = parseInt(in.substring(7));
                return taskList.deleteTask(taskDeleted);
            } catch (NumberFormatException e) {
                return "Invalid Input for delete command";
            }
        }
    }

    /**
     * Adds the given input as a todo task to the list
     * @param in todo task to be added
     * @param taskList
     */
    public static String toDoOutput(String in, TaskList taskList) {
        if (in.length() == 4) {
            return "OOPS!!! The description of a todo cannot be empty.";
        } else {
            return taskList.addToDoTask(in);
        }
    }

    /**
     * Adds the given input as a event task to the list
     * @param in event task to be added
     * @param taskList
     */
    public static String eventOutput(String in, TaskList taskList) {
        int i = in.indexOf("/");
        if (i < 0) {
            return "Time not detected. Please try again";
        } else {
            try {
                if (isValid(in.substring(i + 1, i + 11))) {
                    return taskList.addEventTask(in, i);
                }
            } catch (StringIndexOutOfBoundsException e) {
                return "Invalid date and time. Use yyyy-mm-dd format for date";
            }
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
        if (i < 0) {
            return "Time not detected. Please try again";
        } else {
            try {
                if (isValid(in.substring(i + 1, i + 11))) {
                    return taskList.addDeadlineTask(in, i);
                }
            } catch (StringIndexOutOfBoundsException e) {
                return "Invalid date and time";
            }
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
}
