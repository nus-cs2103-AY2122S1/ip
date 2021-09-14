package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.ArchiveCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListArchiveCommand;
import duke.command.ListCommand;
import duke.command.RemoveArchiveCommand;
import duke.command.RemoveCommand;
import duke.command.UnarchiveCommand;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser which parses the input given by users of Duke.
 */
public class Parser {
    private static final String SPACE = " ";

    // Checks for command types.
    /**
     * Checks if input is a remove command.
     *
     * @param input Input which is being checked.
     * @return true if input is a remove command.
     */
    public static boolean isRemove(String input) {
        String[] separated = input.split(SPACE);
        return separated[0].equals("remove") && separated.length > 1;
    }

    /**
     * Checks if input is a remove archive command.
     *
     * @param input Input which is being checked.
     * @return true if input is a remove archive command.
     */
    public static boolean isRemoveArchive(String input) {
        String[] separated = input.split(SPACE);
        return separated.length > 2 && separated[0].equals("remove") && separated[1].equals("archive");
    }

    /**
     * Checks if input is a done command.
     *
     * @param input Input which is being checked.
     * @return true if input is a done command.
     */
    public static boolean isDone(String input) {
        String[] separated = input.split(SPACE);
        return separated[0].equals("done");
    }

    /**
     * Checks if user input is a find command.
     *
     * @param input User input to check if it is a find command.
     * @return true if it is a find command, else false.
     */
    public static boolean isFind(String input) {
        String[] separated = input.split(SPACE);
        return separated[0].equals("find");
    }

    /**
     * Checks if userInput is a list command.
     *
     * @param userInput Input to be checked.
     * @return true if it is a list command.
     */
    private static boolean isList(String userInput) {
        String[] separated = userInput.split(" ");

        return separated.length == 1 && separated[0].equals("list");
    }

    /**
     * Checks if userInput is a list archive command.
     *
     * @param userInput Input to be checked.
     * @return true if it is a list archive command.
     */
    private static boolean isListArchive(String userInput) {
        String[] separated = userInput.split(" ");

        return separated.length == 2 && separated[1].equals("archive") && isList(separated[0]);
    }

    /**
     * Checks if input is an event command.
     *
     * @param input Input which is being checked.
     * @return true if input is a event command.
     */
    private static boolean isEvent(String input) {
        String[] separated = input.split(SPACE);
        return separated[0].equals("event");
    }

    /**
     * Checks if input is a deadline command.
     *
     * @param input Input which is being checked.
     * @return true if input is a deadline command.
     */
    private static boolean isDeadline(String input) {
        String[] separated = input.split(SPACE);
        return separated[0].equals("deadline");
    }

    /**
     * Checks if input is a todo command.
     *
     * @param input Input which is being checked.
     * @return true if input is a todo command.
     */
    private static boolean isTodo(String input) {
        String[] separated = input.split(SPACE);
        return separated[0].equals("todo");
    }

    /**
     * Checks if input is an archive command.
     *
     * @param input Input which is being checked.
     * @return true if input is an archive command.
     */
    private static boolean isArchive(String input) {
        String[] separated = input.split(" ");
        return separated[0].equals("archive");
    }

    /**
     * Checks if input is an unarchive command.
     *
     * @param input Input which is being checked.
     * @return true if input is an unarchive command.
     */
    private static boolean isUnarchive(String input) {
        String[] separated = input.split(" ");
        return separated[0].equals("unarchive");
    }

    /**
     * Checks if input is a bye command.
     *
     * @param input Input which is being checked.
     * @return true if input is a bye command.
     */
    private static boolean isBye(String input) {
        return input.equals("bye");
    }


    // Checks for conditions.

    /**
     * Checks if dateString is in a valid date form depicted by dateFormatter.
     *
     * @param dateString String which contains a date.
     * @param dateFormatter Format which you want to check if dateString is in.
     * @return true if dateString is in valid form, else otherwise.
     */
    public static boolean isValidDate(String dateString, DateTimeFormatter dateFormatter) {
        try {
            LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if timeString is in a valid date form depicted by timeFormatter.
     *
     * @param timeString String which contains a time.
     * @param timeFormatter Format which you want to check if timeString is in.
     * @return true if timeString is in valid form, else otherwise.
     */
    public static boolean isValidTime(String timeString, DateTimeFormatter timeFormatter) {
        try {
            LocalTime.parse(timeString, timeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if String is an Integer or "all" word.
     *
     * @param s String to check.
     * @return true if it is an Integer or "all" word, else false.
     */
    public static boolean isIntegerOrAll(String s) {
        return isPositiveInteger(s) || isAll(s);
    }

    /**
     * Checks if String is "all" word.
     *
     * @param s String to check.
     * @return true if it is an "all" word.
     */
    public static boolean isAll(String s) {
        return s.equals("all");
    }

    /**
     * Checks if String is an Integer.
     *
     * @param s String to check.
     * @return true if it is an Integer.
     */
    public static boolean isPositiveInteger(String s) {
        return s.matches("\\d+");
    }

    /**
     * Checks if string is within range of given taskList.
     *
     * @param dukeList taskList to see if index s is within.
     * @param s String to check if is within range of taskList.
     * @return true if String is within the range of the taskList, else false.
     */
    public static boolean isOutOfRange(DukeList dukeList, String s) {
        return !isAll(s) && (isExceedLength(dukeList, s) || isLessThanOne(s));
    }

    /**
     * Checks if integer value of s is less than 1.
     *
     * @param s String which is to be converted intp integer value.
     * @return true if integer value of s is less than 1, false otherwise.
     */
    private static boolean isLessThanOne(String s) {
        return Integer.valueOf(s) < 1;
    }

    /**
     * Checks if length of words array is less than two.
     *
     * @param words Words array to check.
     * @return true if length of words array is less than two, else false.
     */
    public static boolean isLengthLessThanTwo(String[] words) {
        return words.length < 2;
    }

    /**
     * Checks if integer value of s exceeds the length of dukeList.
     *
     * @param dukeList DukeList to check if integer value of s exceeds.
     * @param s String which is to be converted into integer value.
     * @return true if integer value of s does not exceed length of dukeList,
     * false otherwise.
     */
    private static boolean isExceedLength(DukeList dukeList, String s) {
        return Integer.valueOf(s) > dukeList.getSize();
    }

    /**
     * Parses the input string and returns a command for Duke to execute.
     *
     * @param userInput Input which needs to be parsed.
     * @param ui Ui object from Duke class.
     * @param taskList TaskList object from Duke class.
     * @param archiveList ArchiveList object from Duke class.
     * @return Command to execute.
     * @throws DukeException If incorrect values are passed for remove or done commands.
     */
    public static Command parse(
            String userInput, Ui ui, TaskList taskList, ArchiveList archiveList) throws DukeException {
        if (Parser.isList(userInput)) {
            return ListCommand.generateCommand(userInput, taskList, archiveList);
        } else if (Parser.isListArchive(userInput)) {
            return ListArchiveCommand.generateCommand(userInput, taskList, archiveList);
        } else if (Parser.isDone(userInput)) {
            return DoneCommand.generateCommand(userInput, taskList, archiveList);
        } else if (Parser.isRemoveArchive(userInput)) {
            return RemoveArchiveCommand.generateCommand(userInput, taskList, archiveList);
        } else if (Parser.isRemove(userInput)) {
            return RemoveCommand.generateCommand(userInput, taskList, archiveList);
        } else if (Parser.isBye(userInput)) {
            return ExitCommand.generateCommand(userInput, taskList, archiveList);
        } else if (Parser.isFind(userInput)) {
            return FindCommand.generateCommand(userInput, taskList, archiveList);
        } else if (Parser.isTodo(userInput)) {
            return AddToDoCommand.generateCommand(userInput, taskList, archiveList);
        } else if (Parser.isDeadline(userInput)) {
            return AddDeadlineCommand.generateCommand(userInput, taskList, archiveList);
        } else if (Parser.isEvent(userInput)) {
            return AddEventCommand.generateCommand(userInput, taskList, archiveList);
        } else if (Parser.isArchive(userInput)) {
            return ArchiveCommand.generateCommand(userInput, taskList, archiveList);
        } else if (Parser.isUnarchive(userInput)) {
            return UnarchiveCommand.generateCommand(userInput, taskList, archiveList);
        } else {
            throw new InvalidCommandException();
        }
    }
}
