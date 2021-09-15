package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.commands.*;

/**
 * Represents the Parser class which makes sense of what the user typed.
 */
public class Parser {

    /**
     * Method to parse and make sense what the user typed in.
     *
     * @param commandLine The user's input.
     * @param tasks       The TaskList that contains all the user's tasks.
     * @return Command deciphered from the user input.
     * @throws DukeException If the user gives a bad input.
     */
    public Command getCommand(String commandLine, TaskList tasks) throws DukeException {
        String[] fullCommand = commandLine.split(" ");
        String command = fullCommand[0];

        switch (command) {
        case "exit":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            hasIndex(fullCommand);

            ArrayList<Integer> doneIndexes = parseIndexes(fullCommand, tasks.size());
            return new DoneCommand(doneIndexes);
        case "delete":
            hasIndex(fullCommand);

            ArrayList<Integer> deleteIndexes = parseIndexes(fullCommand, tasks.size());
            return new DeleteCommand(deleteIndexes);
        case "todo":
            String todoDesc;
            todoDesc = commandLine.replace("todo", "").trim();

            if (todoDesc.equals("")) {
                throw new DukeException("A to-do needs a description");
            }

            return new AddTodoCommand(todoDesc);
        case "deadline":
            String deadlineDesc;
            String by;
            String[] deadlineArgs = commandLine.replace("deadline ", "").split("/by ");

            if (deadlineArgs.length != 2) {
                throw new DukeException("Invalid format for deadline");
            }

            deadlineDesc = deadlineArgs[0];
            by = deadlineArgs[1];

            LocalDateTime deadlineDate = this.parseDate(by);
            return new AddDeadlineCommand(deadlineDesc, deadlineDate);
        case "event":
            String eventDesc;
            String at;

            String[] eventArgs = commandLine.replace("event ", "").split("/at ");

            if (eventArgs.length != 2) {
                throw new DukeException("Invalid format for event");
            }

            eventDesc = eventArgs[0];
            at = eventArgs[1];

            if (eventDesc.equals("")) {
                throw new DukeException("An event needs a description");
            }

            LocalDateTime eventDate = this.parseDate(at);
            return new AddEventCommand(eventDesc, eventDate);
        case "find":
            String keyword;
            keyword = commandLine.replace("find", "").trim();
            return new FindCommand("find", keyword);
        default:
            throw new DukeException("I do not understand that command");
        }
    }

    /**
     * Checks if a done/delete command has an index.
     *
     * @param fullCommand The full commandline input by the user
     * @throws DukeException If there is no index number
     */
    public static void hasIndex(String[] fullCommand) throws DukeException {
        if (fullCommand.length == 1) {
            throw new DukeException("Please give an index number");
        }
    }

    /**
     * Checks if the index number that the user input is valid.
     *
     * @param i            The index number that the user input.
     * @param lengthOfList The length of the user's TaskList.
     * @throws DukeException If the index number is negative or more than length of the TaskList.
     */
    public static void isValidIndex(int i, int lengthOfList) throws DukeException {
        if (i <= 0) {
            throw new DukeException("Please give an index number > 0");
        } else if (i > lengthOfList) {
            throw new DukeException("Maximum index number is " + lengthOfList);
        }
    }

    /**
     * Parses the date.
     *
     * @param date The date of the task in String format.
     * @return LocalDateTime of the task.
     * @throws DukeException If the date format is invalid
     */
    public LocalDateTime parseDate(String date) throws DukeException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[d/M/[uuuu][uu] H:mm]");
        try {
            return LocalDateTime.parse(date, dtf);
        } catch (DateTimeParseException err) {
            throw new DukeException("Invalid date format\n"
                    + "Please format date as the following:\n"
                    + "Day/Month/Year H:mm, in 24-hour format\n"
                    + "e.g 24/9/21 16:30\n");
        }
    }

    /**
     * Parses the indexes passed into done/delete command.
     *
     * @param fullCommand The full commandline input by the user
     * @param taskSize    The size of the task list
     * @return An ArrayList containing the indexes
     * @throws DukeException If there is an invalid index
     */
    public ArrayList<Integer> parseIndexes(String[] fullCommand, int taskSize) throws DukeException {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 1; i < fullCommand.length; i++) {
            int index = Integer.parseInt(fullCommand[i]);
            isValidIndex(index, taskSize);

            if (temp.contains(index)) {
                throw new DukeException("No duplicate indexes allowed");
            }
            temp.add(index);
        }
        return temp;
    }
}
