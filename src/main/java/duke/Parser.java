package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * This is the class that handles user input.
 */
public class Parser {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Returns the corresponding message based on the user input.
     *
     * @param input The user input.
     * @param bot The chat bot that handles all the commands
     * @throws DukeException Custom duke error
     */
    public String parse (String input, ChatBot bot) throws DukeException {
        String[] inputs = input.split(" ", 2);
        String key = inputs[0];

        // todo use enum here
        // can make key.toUpper and pass in the values.
        switch (key) {
        case "bye":
            return bot.handleExit();
        case "list":
            return bot.handleList();
        case "done":
            // check if a number is specified
            if (checkLength(inputs)) {
                throw new DukeException("The task number cannot be empty you dum dum");
            }
            // check if the following string is a number
            try {
                int index = Integer.parseInt(inputs[1]);
                if (checkIndex(index, bot.getTotalTasks())) {
                    throw new DukeException("Please enter a valid number");
                }
                return bot.handleDone(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Please use numbers for the task number");
            }
        case "deadline":
            if (checkLength(inputs)) {
                throw new DukeException("Please specify the deadline description");
            }
            String[] info = inputs[1].split("/by", 0);
            if (checkLength(info)) {
                throw new DukeException("Please specify the deadline time");
            }
            try {
                LocalDateTime parsedDate = LocalDateTime.parse(info[1].trim(), formatter);
                return bot.handleDeadline(info[0].trim(), parsedDate);
            } catch (DateTimeParseException e) {
                throw new DukeException("The format of date & time is wrong. Please use {dd/mm/yyyy hhmm}");
            }
        case "todo":
            if (checkLength(inputs)) {
                throw new DukeException("Please specify the todo description");
            }
            return bot.handleTodo(inputs[1]);
        case "event":
            if (checkLength(inputs)) {
                throw new DukeException("Please specify the event description");
            }
            info = inputs[1].split("/at");
            if (checkLength(info)) {
                throw new DukeException("Please specify the time");
            }
            try {
                LocalDateTime parsedDate = LocalDateTime.parse(info[1].trim(), formatter);
                return bot.handleEvent(info[0].trim(), parsedDate);
            } catch (DateTimeParseException e) {
                throw new DukeException("The format of date & time is wrong. Please use {dd/mm/yyyy hhmm}");
            }
        case "delete":
            if (checkLength(inputs)) {
                throw new DukeException("The task number to delete cannot be empty you dum dum");
            }
            try {
                int index = Integer.parseInt(inputs[1]);
                if (checkIndex(index, bot.getTotalTasks())) {
                    throw new DukeException("Please enter a valid number");
                }
                return bot.handleDelete(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Please use numbers for the task number");
            }
        case "find":
            if (checkLength(inputs)) {
                throw new DukeException("The task to find cannot be empty!");
            }
            return bot.handleFind(inputs[1].trim());
        case "sort":
            if (checkLength(inputs)) {
                throw new DukeException("Please specify the type of sort (Name, Type)");
            }
            return bot.handleSort(inputs[1].trim());
        default:
            return bot.handleWrongCommand();
        }
    }

    /**
     * Returns a boolean, true if the string array is of length 1 false if otherwise.
     *
     * @param inputs The string array
     * @return If string array is of length 1.
     */
    public boolean checkLength(String[] inputs) {
        return inputs.length == 1;
    }

    /**
     * Checks if the index is less than equals to 0 or more than the total number of tasks.
     *
     * @param index Index to check
     * @param totalTasks Total number of Tasks.
     * @return If the index is valid.
     */
    public boolean checkIndex(int index, int totalTasks) {
        return index <= 0 || index > totalTasks;
    }
}
