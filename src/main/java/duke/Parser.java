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
     * This method calls the corresponding ChatBot methods based on the user input.
     *
     * @param input The user input.
     * @param bot
     * @throws DukeException
     */
    public void parse (String input, ChatBot bot) throws DukeException {
        String[] inputs = input.split(" ", 2);
        String key = inputs[0];
        int run = 1;

        switch (key) {
        case "bye":
            bot.handleExit();
            run = 0;
            break;
        case "list":
            bot.handleList();
            break;
        case "done":
            // check if a number is specified
            if (inputs.length == 1) {
                throw new DukeException("The task number cannot be empty you dum dum");
            }
            // check if the following string is a number
            int index = Integer.parseInt(inputs[1]);
            // check if the number is valid.
            if (index <= 0 || index > bot.getTotalTasks()) {
                throw new DukeException("Please enter a valid number");
            }
            bot.handleDone(index);
            break;
        case "deadline":
            if (inputs.length == 1) {
                throw new DukeException("Please specify the deadline description");
            }
            String[] info = inputs[1].split("/by", 0);
            if (info.length == 1) {
                throw new DukeException("Please specify the deadline time");
            }
            try {
                LocalDateTime parsedDate = LocalDateTime.parse(info[1].trim(), formatter);
                bot.handleDeadline(info[0].trim(), parsedDate);
            } catch (DateTimeParseException e) {
                throw new DukeException("The format of date & time is wrong. Please use {dd/mm/yyyy hhmm}");
            }
            break;
        case "todo":
            if (inputs.length == 1) {
                throw new DukeException("Please specify the todo description");
            }
            bot.handleTodo(inputs[1]);
            break;
        case "event":
            if (inputs.length == 1) {
                throw new DukeException("Please specify the event description");
            }
            info = inputs[1].split("/at");
            if (info.length == 1) {
                throw new DukeException("Please specify the time");
            }
            try {
                LocalDateTime parsedDate = LocalDateTime.parse(info[1].trim(), formatter);
                bot.handleEvent(info[0].trim(), parsedDate);
            } catch (DateTimeParseException e) {
                throw new DukeException("The format of date & time is wrong. Please use {dd/mm/yyyy hhmm}");
            }
            break;
        case "delete":
            if (inputs.length == 1) {
                throw new DukeException("The task number to delete cannot be empty you dum dum");
            }
            // check if the following string is a number
            index = Integer.parseInt(inputs[1]);
            // check if the number is valid.
            if (index <= 0 || index > bot.getTotalTasks()) {
                throw new DukeException("Please enter a valid number");
            }
            bot.handleDelete(index);
            break;
        case "find":
            if (inputs.length == 1) {
                throw new DukeException("The task to find cannot be empty!");
            }
            bot.handleFind(inputs[1].trim());
            break;
        default:
            bot.handleWrongCommand();
            break;
        }
    }
}
