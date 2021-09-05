package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;

/**
 * Represents the Parser class which makes sense of what the user typed.
 */
public class Parser {

    /**
     * Method to parse and make sense what the user typed in.
     *
     * @param commandLine the user's input.
     * @param tasks       the TaskList that contains all the user's tasks.
     * @return Command command deciphered from the user input.
     * @throws DukeException If the user gives a bad input.
     */
    public Command getCommand(String commandLine, TaskList tasks) throws DukeException {
        String[] fullCommand = commandLine.split(" ");
        String command = fullCommand[0];
        String desc;

        switch (command) {
        case "list":
            return new ListCommand("list");
        case "done":
            if (fullCommand.length == 1) {
                throw new DukeException("Please give an index number");
            }
            int doneIndex = Integer.parseInt(fullCommand[1]);
            checkIndex(doneIndex, tasks.size());
            return new DoneCommand("done", doneIndex);
        case "delete":
            if (fullCommand.length == 1) {
                throw new DukeException("Please give an index number");
            }
            int deleteIndex = Integer.parseInt(fullCommand[1]);
            checkIndex(deleteIndex, tasks.size());
            return new DeleteCommand("delete", deleteIndex);
        case "todo":
            desc = commandLine.replace("todo", "").trim();
            if (desc.equals("")) {
                throw new DukeException("A to-do needs a description");
            }
            return new AddTodoCommand(desc);
        case "deadline":
            String by;

            String[] deadlineArgs = commandLine.replace("deadline ", "").split("/by ");

            if (deadlineArgs.length != 2) {
                throw new DukeException("Invalid format for deadline");
            }

            desc = deadlineArgs[0];
            by = deadlineArgs[1];

            LocalDateTime deadlineDate = this.parseDate(by);
            return new AddDeadlineCommand(desc, deadlineDate);

        case "event":
            String at;

            String[] eventArgs = commandLine.replace("event ", "").split("/at ");

            if (eventArgs.length != 2) {
                throw new DukeException("Invalid format for event");
            }

            desc = eventArgs[0];
            at = eventArgs[1];

            if (desc.equals("")) {
                throw new DukeException("An event needs a description");
            }

            LocalDateTime eventDate = this.parseDate(at);
            return new AddEventCommand(desc, eventDate);

        case "find":
            desc = commandLine.replace("find", "").trim();
            return new FindCommand("find", desc);

        default:
            throw new DukeException("I do not understand that command");
        }
    }

    /**
     * Checks if the index number that the user input is valid.
     *
     * @param i            the index number that the user input.
     * @param lengthOfList the length of the user's TaskList.
     * @throws DukeException If the index number is negative or more than length of the TaskList.
     */
    public static void checkIndex(int i, int lengthOfList) throws DukeException {
        if (i <= 0) {
            throw new DukeException("Please give an index number > 0");
        } else if (i > lengthOfList) {
            throw new DukeException("Maximum index number is " + lengthOfList);
        }
    }

    /**
     * Parses the date
     * @param date The date of the task in String format.
     * @return LocalDateTime of the task.
     * @throws DukeException If the date format is invalid
     */
    public LocalDateTime parseDate(String date) throws DukeException {
        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("[d/M/[uuuu][uu] H:mm][d-MMM-[uuuu][uu] H:mm][d-M-[uuuu][uu] H:mm][d/MMM/[uuuu][uu] H:mm]");
        try {
            return LocalDateTime.parse(date, dtf);
        } catch (DateTimeParseException err) {
            throw new DukeException("Invalid date format\nPlease format date as the following:\n"
                    + "Day/Month/Year H:mm or\n"
                    + "Day-Month-Year H:mm, in 24-hour format\n"
                    + "Month can be 3-letter word or a number "
                    + "(e.g Jul or 7 or 07)");
        }
    }
}
