package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.MarkDoneCommand;
import duke.command.PrintCommand;
import duke.exception.DukeException;
import duke.exception.IllegalFormatException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents the parser that could interpret the user input.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Parser {
    /**
     * Parses the input string given by the user and returns the respective commands.
     *
     * @param input The input given by user in the console.
     * @return A command corresponding to the interpreted intention of the user input.
     * @throws DukeException If an error is encountered.
     */
    public static Command parse(String input) throws DukeException {
        String[] splitWord = input.split(" ", 2);
        String firstWord = splitWord[0];

        if (input.equals("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new PrintCommand();
        }
        if (firstWord.equals("done")) {
            int taskToBeMarkDone = getTaskToBeMarkedDoneIndex(input);
            return new MarkDoneCommand(taskToBeMarkDone);
        }
        if (firstWord.equals("todo")) {
            Task taskToBeAdded = getTodoToBeAdded(input);
            return new AddCommand(taskToBeAdded);
        }
        if (firstWord.equals("deadline")) {
            Task taskToBeAdded = getDeadlineToBeAdded(input);
            return new AddCommand(taskToBeAdded);
        }
        if (firstWord.equals("event")) {
            Task taskToBeAdded = getEventToBeAdded(input);
            return new AddCommand(taskToBeAdded);
        }
        if (firstWord.equals("delete")) {
            int taskIndexToBeRemoved = getTaskToBeDeletedIndex(input);
            return new DeleteCommand(taskIndexToBeRemoved);
        }
        if (firstWord.equals("find")) {
            String keyword = getKeyword(input);
            return new FindCommand(keyword);
        }
        if (input.equals("help")) {
            return new HelpCommand();
        }
        throw new UnknownCommandException();
    }

    private static int getTaskToBeMarkedDoneIndex(String input) throws IllegalFormatException {
        String[] splitCommand = input.split(" ");
        if (splitCommand.length != 2) {
            throw new IllegalFormatException("OOPS!!! Specify task to be mark done in the correct format.");
        }
        String secondWord = splitCommand[1];
        boolean isAllDigitInSecondWord = checkIfAllDigit(secondWord);
        if (!isAllDigitInSecondWord) {
            throw new IllegalFormatException("OOPS!!! Specify the task to be mark done as a number.");
        }
        return Integer.parseInt(secondWord) - 1;
    }

    private static Task getTodoToBeAdded(String input) throws IllegalFormatException {
        String[] splitCommand = input.split(" ", 2);
        if (splitCommand.length == 1) {
            throw new IllegalFormatException("OOPS!!! The description of a todo cannot be empty.");
        }
        String toDoDescription = input.split(" ", 2)[1];
        if (toDoDescription.length() == 0) {
            throw new IllegalFormatException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(toDoDescription);
    }

    private static Task getDeadlineToBeAdded(String input) throws IllegalFormatException {
        String[] splitCommand = input.split(" ", 2);
        if (splitCommand.length == 1) {
            throw new IllegalFormatException("OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] splitTask = splitCommand[1].split(" /by ", 2);
        String deadlineDescription = splitTask[0];
        if (deadlineDescription.length() == 0) {
            throw new IllegalFormatException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (splitTask.length == 1) {
            throw new IllegalFormatException("OOPS!!! Specify the deadline date in the format yyyy-mm-dd.");
        }

        LocalDate deadlineDateTime;
        try {
            deadlineDateTime = LocalDate.parse(splitTask[1]);
        } catch (DateTimeParseException e) {
            throw new IllegalFormatException("OOPS!!! Specify the deadline date in the format yyyy-mm-dd.");
        }

        return new Deadline(deadlineDescription, deadlineDateTime);
    }

    private static Task getEventToBeAdded(String input) throws IllegalFormatException {
        String[] splitCommand = input.split(" ", 2);
        if (splitCommand.length == 1) {
            throw new IllegalFormatException("OOPS!!! The description of a event cannot be empty.");
        }

        String[] splitTask = splitCommand[1].split(" /at ", 2);
        String eventDescription = splitTask[0];
        if (eventDescription.length() == 0) {
            throw new IllegalFormatException("OOPS!!! The description of a event cannot be empty.");
        }
        if (splitTask.length == 1) {
            throw new IllegalFormatException("OOPS!!! Specify the event date in the format yyyy-mm-dd.");
        }

        LocalDate eventDateTime;
        try {
            eventDateTime = LocalDate.parse(splitTask[1]);
        } catch (DateTimeParseException e) {
            throw new IllegalFormatException("OOPS!!! Specify the event date in the format yyyy-mm-dd.");
        }
        return new Event(eventDescription, eventDateTime);
    }

    private static int getTaskToBeDeletedIndex(String input) throws IllegalFormatException {
        String[] splitCommand = input.split(" ");
        if (splitCommand.length != 2) {
            throw new IllegalFormatException("OOPS!!! Specify task to be removed in the correct format.");
        }
        String secondWord = splitCommand[1];

        boolean isAllDigitInSecondWord = checkIfAllDigit(secondWord);
        if (!isAllDigitInSecondWord) {
            throw new IllegalFormatException("OOPS!!! Specify the task to be removed as a number.");
        }
        return Integer.parseInt(secondWord) - 1;
    }

    private static String getKeyword(String input) throws IllegalFormatException {
        String[] splitCommand = input.split(" ");
        if (splitCommand.length != 2) {
            throw new IllegalFormatException("OOPS!!! Specify one keyword to be searched.");
        }
        String keyword = splitCommand[1];
        return keyword;
    }

    private static boolean checkIfAllDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
