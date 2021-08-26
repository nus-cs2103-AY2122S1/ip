package Duke.Main;

import Duke.Command.*;
import Duke.DukeException.DukeIncompleteException;
import Duke.DukeException.DukeSyntaxErrorException;
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

    public static Command parse(String command, TaskList taskList) {
        String[] components = command.split(" ", 2);
        String type = components[0].toLowerCase().trim();
        String description = (components.length < 2) ? "" : components[1].trim();
        switch (type) {
            case "help":
                return new HelpCommand();
            case "list":
                return new ListCommand(taskList);
            case "done":
                return new DoneCommand(description, taskList);
            case "todo":
                return new TodoCommand(description, taskList);
            case "deadline":
                return new DeadlineCommand(description, taskList);
            case "event":
                return new EventCommand(description, taskList);
            case "delete":
                return new DeleteCommand(description, taskList);
            case "find":
                return new FindCommand(description, taskList);
            default:
                throw new DukeSyntaxErrorException(type);
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
}
