package Duke.Main;

import Duke.Task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    /**
     * Convert YYYY-MM-DD format to MMM dd YYY format (for representing time in Duke)
     * @param date date in (yyyy-mm-dd) format
     * @return date in MMM dd YYY format
     */
    public static String convert(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Convert MMM dd YYY format to YYYY-MM-DD format (for saving time to file)
     * @param date Date entered in (MMM dd yyyy) format
     * @return Date in (YYYY-MM-DD) format
     */
    public static String reverse(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM dd yyyy"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Process incoming command from user and prompt an appropriate reply with task list
     * @param command User command entered
     * @param taskList Task List for interaction and action
     * @return reply to user command
     * @throws DukeException if command is not recognizable
     */
    public static String parse(String command, TaskList taskList) {
        String[] components = command.split(" ", 2);
        String type = components[0].toLowerCase().trim();
        String description = (components.length < 2) ? "" : components[1].trim();
        switch (type) {
        case "help":
            return Storage.processInstructions();
        case "list":
            return taskList.printList();
        case "done":
            if (description.trim().equalsIgnoreCase("all")) {
                return taskList.doneAll();
            } else {
                return taskList.done(getNum(description));
            }
        case "todo":
            return taskList.addTask(description, Task.Type.TODO);
        case "deadline":
            return taskList.addTask(description, Task.Type.DEADLINE);
        case "event":
            return taskList.addTask(description, Task.Type.EVENT);
        case "delete":
            if (description.trim().equalsIgnoreCase("all")) {
                return taskList.deleteAll();
            } else {
                return taskList.delete(getNum(description));
            }
        case "find":
            return taskList.find(description);
        default:
            throw new DukeException("Error: ", DukeException.Type.SYNTAX_ERROR);
        }
    }

    /**
     * Check whether a command entered is exit command ("bye")
     * @param command User command entered
     * @return whether command is equals to bye
     */
    public static boolean isExit(String command) {
        return command.equalsIgnoreCase("bye");
    }

    /**
     * Convert Numeric string to int value or throw syntax-error exception
     * @param val a String that represents the index number in task
     * @return int value of the String entered
     * @throws DukeException if command is incomplete or invalid (not a number)
     */
    private static int getNum(String val) {
        try {
            if (val.equalsIgnoreCase("")) {
                throw new DukeException("Error", DukeException.Type.INCOMPLETE);
            }
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            throw new DukeException("Error", DukeException.Type.SYNTAX_ERROR);
        }
    }
}
