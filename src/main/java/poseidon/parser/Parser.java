package poseidon.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import poseidon.command.AddDeadline;
import poseidon.command.AddEvent;
import poseidon.command.AddTodo;
import poseidon.command.Bye;
import poseidon.command.Command;
import poseidon.command.Delete;
import poseidon.command.Done;
import poseidon.command.Fail;
import poseidon.command.Find;
import poseidon.command.Help;
import poseidon.command.List;
import poseidon.command.Sort;
import poseidon.exception.PoseidonException;

/**
 * Represents a {@code Parser} object for all parsing related operations.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Parser {

    /**
     * Returns a {@code String} array that contains the useful and necessary parts of a command to be executed.
     *
     * @param newCommand {@code String} version of a command.
     * @return {@code String} array.
     */
    public static Command parse(String newCommand) {
        if (AddDeadline.isThisCmd(newCommand)) {
            return new AddDeadline(newCommand);
        } else if (AddEvent.isThisCmd(newCommand)) {
            return new AddEvent(newCommand);
        } else if (AddTodo.isThisCmd(newCommand)) {
            return new AddTodo(newCommand);
        } else if (Bye.isThisCmd(newCommand)) {
            return new Bye(newCommand);
        } else if (Delete.isThisCmd(newCommand)) {
            return new Delete(newCommand);
        } else if (Done.isThisCmd(newCommand)) {
            return new Done(newCommand);
        } else if (Find.isThisCmd(newCommand)) {
            return new Find(newCommand);
        } else if (Help.isThisCmd(newCommand)) {
            return new Help(newCommand);
        } else if (List.isThisCmd(newCommand)) {
            return new List(newCommand);
        } else if (Sort.isThisCmd(newCommand)) {
            return new Sort(newCommand);
        } else {
            return new Fail(newCommand);
        }
    }

    /**
     * Returns a {@code LocalDateTime} object after parsing a string version of date and time.
     *
     * @param dateTime {@code String} version of date and time.
     * @return {@code LocalDateTime} object.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        final String dateFormat = "yyyy MM dd HHmm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime deadlineDT;

        try {
            deadlineDT = LocalDateTime.parse(dateTime, formatter);
            return deadlineDT;
        } catch (DateTimeException ex) {
            throw new PoseidonException(ex.getMessage() + "\n"
                    + "Please try again.");
        }
    }

    /**
     * Returns the {@code Integer} version of a {@code String} number.
     *
     * @param intString {@code String} version of a number.
     * @return {@code Integer} version of the given number.
     */
    public static int parseIndex(String intString) {
        return Integer.parseInt(intString.trim());
    }

    /**
     * Returns true if the given user {@code String} input is a valid "bye" command by pattern matching.
     *
     * @param newCommand {@code String} user input.
     * @return {@code Boolean} validation result.
     */
    public static boolean isParsedBye(String newCommand) {
        return Bye.isThisCmd(newCommand);
    }
}
