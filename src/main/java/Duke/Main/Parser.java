package Duke.Main;

import Duke.Task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static String convert(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Convert MMM dd YYY to YYYY-MM-DD
     */
    public static String reverse(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM dd yyyy"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

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
        default:
            throw new DukeException("Error: ", DukeException.Type.SYNTAX_ERROR);
        }
    }

    public static boolean isExit(String command) {
        return command.equalsIgnoreCase("bye");
    }

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
