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
     * @param ui Ui to close when necessary
     * @param tasklist The list that is adjusted when a specific input is given
     */
    public static void parse(String in, Ui ui, TaskList tasklist) {
        try {
            if (in.equals("bye") || in.equals("Bye")) {
                Parser.byeOutput();
                ui.input.close();
            } else if (in.equals("list") || in.equals("List")) {
                Parser.listOutput(tasklist);
            } else if (in.substring(0, 4).equals("done")) {
                Parser.doneOutput(in, tasklist);
            } else if (in.substring(0, 6).equals("delete")) {
                Parser.deleteOutput(in, tasklist);
            } else if (in.substring(0, 4).equals("todo")) {
                Parser.toDoOutput(in, tasklist);
            } else if (in.substring(0, 5).equals("event")) {
                Parser.eventOutput(in, tasklist);
            } else if (in.substring(0, 8).equals("deadline")) {
                Parser.deadlineOutput(in, tasklist);
            } else {
                Parser.invalidInputResponse();
            }
        } catch (StringIndexOutOfBoundsException e) {
            Parser.invalidInputResponse();
        }
    }

    /**
     * The default message when the input is not a proper command
     */
    public static void invalidInputResponse() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Exits Duke and closes the scanner
     */
    public static void byeOutput() {
        System.out.println("Bye. Hope to see you again!");
    }

    /**
     * Lists out the items in the list
     * @param tasklist items to be listed
     */
    public static void listOutput(TaskList tasklist) {
        tasklist.showList();
    }

    /**
     * Marks the given task as done
     * @param in task to be marked as done
     * @param taskList
     */
    public static void doneOutput(String in, TaskList taskList) {
        if (in.length() < 6) {
            System.out.println("Invalid Input for done command");
        } else {
            int taskDone = parseInt(in.substring(5));
            if (taskDone > 100) {
                System.out.println("Invalid Input for done command");
            } else {
                taskList.doTask(taskDone);
            }
        }
        //System.out.println(taskDone);
    }

    /**
     * Deletes the given task
     * @param in task to be marked as deleted
     * @param taskList
     */
    public static void deleteOutput(String in, TaskList taskList) {
        if (in.length() < 8) {
            System.out.println("Invalid Input for delete command");
        } else {
            try {
                int taskDeleted = parseInt(in.substring(7));
                taskList.deleteTask(taskDeleted);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input for delete command");
            }
        }
    }

    /**
     * Adds the given input as a todo task to the list
     * @param in todo task to be added
     * @param taskList
     */
    public static void toDoOutput(String in, TaskList taskList) {
        if (in.length() == 4) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            taskList.addToDoTask(in);
        }
    }

    /**
     * Adds the given input as a event task to the list
     * @param in event task to be added
     * @param taskList
     */
    public static void eventOutput(String in, TaskList taskList) {
        int i = in.indexOf("/");
        if (i < 0) {
            System.out.println("Time not detected. Please try again");
        } else {
            try {
                if (isValid(in.substring(i + 1, i + 11))) {
                    taskList.addEventTask(in, i);
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Invalid date and time. Use yyyy-mm-dd format for date");
            }
        }
    }

    /**
     * Adds the given input as a deadline task to the list
     * @param in deadline task to be added
     * @param taskList
     */
    public static void deadlineOutput(String in, TaskList taskList) {
        int i = in.indexOf("/");
        if (i < 0) {
            System.out.println("Time not detected. Please try again");
        } else {
            try {
                if (isValid(in.substring(i + 1, i + 11))) {
                    taskList.addDeadlineTask(in, i);
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Invalid date and time");
            }
        }
    }
}
