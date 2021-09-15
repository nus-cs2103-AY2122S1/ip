package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.data.DukeException;
import duke.data.TaskList;

/**
 * Encapsulates a parser to help make sense of user input.
 * This class helps to split the command inputs of the user,
 * for further usage by other classes.
 *
 * @author Jason Ng
 * @version Duke Level-10
 */
public class Parser {
    /** Special names that are the commands for Duke */
    enum SPECIAL_TASK {
        bye,
        done,
        list,
        todo,
        deadline,
        event,
        delete,
        find
    }
    /** Date/time format to be parsed/stored */
    protected DateTimeFormatter df;

    /**
     * Constructor for a parser.
     */
    public Parser() {
        this.df = DateTimeFormatter.ofPattern("yyyy-M-d H:mm");
    }

    /**
     * Returns a string array that contains the type of the command
     * as its first element, and the remaining parts of the string
     * as the second element.
     *
     * @param input The string input.
     * @return The array of strings after splitting the input.
     */
    public String[] splitType(String input) {
        return input.split(" ", 2);
    }

    /**
     * Returns the index of the task.
     * Parsed from an input that is already split based on type.
     *
     * @param typeInput The array of strings that is already split based on type.
     * @return The index of the task.
     */
    public int getIndex(String[] typeInput) {
        return Integer.parseInt(typeInput[1]) - 1;
    }

    /**
     * Checks whether task input has a valid description.
     *
     * @param typeInput The array of strings that is already split based on type.
     * @param type The type of the task.
     * @throws DukeException If description is empty or just a whitespace character.
     */
    public void checkDesc(String[] typeInput, String type) throws DukeException {
        if (typeInput.length < 2 || typeInput[1].equals("") || typeInput[1].equals(" ")) {
            throw new DukeException("The description of " + type + " cannot be empty!");
        }
    }

    /**
     * Returns an array of strings that is further split after being split by type.
     * Further splitting based on input regex.
     *
     * @param input The string to be further split.
     * @param regex The regex to split the string at.
     * @return The string that is split at the input regex.
     */
    public String[] furtherSplit(String input, String regex) {
        return input.split(regex, 2);
    }

    /**
     * Checks whether description is valid for special tasks.
     *
     * @param furtherInput The array of strings that is already split by type and regex.
     * @param type The type of the task.
     * @throws DukeException If description for special task is empty or separator is wrong.
     */
    public void checkFurtherDesc(String[] furtherInput, String type) throws DukeException {
        if (furtherInput.length < 2 || furtherInput[0].equals("")) {
            throw new DukeException("The description of a " + type + " cannot be empty.\n"
                    + "Don't forget to use /by to indicate the deadline.");
        } else if (furtherInput[1].equals("") || furtherInput[1].equals(" ")) {
            throw new DukeException("Must come with a input date/time for the " + type + " .");
        }
    }

    /**
     * Parses for the time based on the string input.
     * Format of time to be parsed stored in the attribute of this class.
     *
     * @param timeString The string input to be parsed.
     * @return The LocalDateTime object parsed from the string input.
     */
    public LocalDateTime parseTime(String timeString) {
        return LocalDateTime.parse(timeString.stripLeading(), df);
    }

    /**
     * Checks if input is a "bye" command.
     *
     * @param input The input string.
     * @return True if it is a "bye" command, false otherwise.
     */
    public boolean isBye(String input) {
        return input.equals(SPECIAL_TASK.bye.name());
    }

    /**
     * Checks if input is a "done" command.
     *
     * @param input The input string.
     * @return True if it is a "done" command, false otherwise.
     */
    public boolean isDone(String input) {
        return input.equals(SPECIAL_TASK.done.name());
    }

    /**
     * Checks if input is a "list" command.
     *
     * @param input The input string.
     * @return True if it is a "list" command, false otherwise.
     */
    public boolean isList(String input) {
        return input.equals(SPECIAL_TASK.list.name());
    }

    /**
     * Checks if input is a "todo" command.
     *
     * @param input The input string.
     * @return True if it is a "todo" command, false otherwise.
     */
    public boolean isTodo(String input) {
        return input.equals(SPECIAL_TASK.todo.name());
    }

    /**
     * Checks if input is a "deadline" command.
     *
     * @param input The input string.
     * @return True if it is a "deadline" command, false otherwise.
     */
    public boolean isDeadline(String input) {
        return input.equals(SPECIAL_TASK.deadline.name());
    }

    /**
     * Checks if input is a "event" command.
     *
     * @param input The input string.
     * @return True if it is a "event" command, false otherwise.
     */
    public boolean isEvent(String input) {
        return input.equals(SPECIAL_TASK.event.name());
    }

    /**
     * Checks if input is a "delete" command.
     *
     * @param input The input string.
     * @return True if it is a "delete" command, false otherwise.
     */
    public boolean isDelete(String input) {
        return input.equals(SPECIAL_TASK.delete.name());
    }

    /**
     * Checks if input is a "find" command.
     *
     * @param input The input string.
     * @return True if it is a "find", false otherwise.
     */
    public boolean isFind(String input) {
        return input.equals(SPECIAL_TASK.find.name());
    }

    /**
     * Checks if input is a "blah".
     *
     * @param input The input string.
     * @return True if it is a "blah", false otherwise.
     */
    public boolean isBlah(String input) {
        return input.equals("Blah");
    }

    /**
     * Parses a "todo" command.
     *
     * @param splitInput The string input that is already split.
     * @throws DukeException If the command is not entered correctly.
     */
    public void parseTodo(String[] splitInput) throws DukeException {
        checkDesc(splitInput, SPECIAL_TASK.todo.name());
    }

    /**
     * Parses a "deadline" command.
     * Returns an array of strings split by the designated separator.
     *
     * @param splitInput The string input that is already split.
     * @return The array of strings split by the designated separator.
     * @throws DukeException If the command is not entered correctly.
     */
    public String[] parseDeadline(String[] splitInput) throws DukeException {
        checkDesc(splitInput, SPECIAL_TASK.deadline.name());
        String[] furtherSplits = furtherSplit(splitInput[1], "/by");
        checkFurtherDesc(furtherSplits, SPECIAL_TASK.deadline.name());
        return furtherSplits;
    }

    /**
     * Parses a "event" command.
     * Returns an array of strings split by the designated separator.
     *
     * @param splitInput The string input that is already split.
     * @return The array of strings split by the designated separator.
     * @throws DukeException If the command is not entered correctly.
     */
    public String[] parseEvent(String[] splitInput) throws DukeException {
        checkDesc(splitInput, SPECIAL_TASK.event.name());
        String[] furtherSplits = furtherSplit(splitInput[1], "/at");
        checkFurtherDesc(furtherSplits, SPECIAL_TASK.event.name());
        return furtherSplits;
    }

    /**
     * Parses a "delete" command.
     * Returns an array of strings split by the designated separator.
     *
     * @param splitInput The string input that is already split.
     * @return The array of strings split by the designated separator.
     * @throws DukeException If the command is not entered correctly.
     */
    public int parseDelete(String[] splitInput) throws DukeException {
        checkDesc(splitInput, SPECIAL_TASK.delete.name());
        int index = getIndex(splitInput);
        return index;
    }

    /**
     * Parses a "find" command.
     *
     * @param splitInput The string input that is already split.
     * @throws DukeException If the command is not entered correctly.
     */
    public void parseFind(String[] splitInput) throws DukeException {
        checkDesc(splitInput, SPECIAL_TASK.find.name());
    }

    /**
     * Checks that entered index of the task  is valid.
     *
     * @param index The input index.
     * @param taskList The taskList to check the index from.
     * @throws DukeException If index outside the range of number of tasks in the taskList.
     */
    public void checkTaskIndex(int index, TaskList taskList) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            throw new DukeException("duke.commands.Task number does not exist!");
        }
    }
}
