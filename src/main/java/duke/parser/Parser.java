package duke.parser;

import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidTimeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Parser object that deals with making sense of the user commands.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class Parser {

    /**
     * Accepts only the specified following specific commands.
     */
    public enum Keyword {
        TODO, EVENT, DEADLINE, LIST, DONE, DELETE, BYE, FIND, HELP
    }

    /** TaskList object that stores tasks */
    private static TaskList tasks;
    /** Storage object that saves the TaskList. */
    private Storage storage;
    /** Ui object that deals with user commands. */
    private Ui ui;

    /**
     * Constructor for a Parser object.
     *
     * @param tasks TaskList containing the list of tasks.
     * @param storage Storage that stores the data.
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = new Ui();
    }

    private String getFirstWord(String input) {
        String[] arr = input.split(" ", 2);
        String firstWord = arr[0];
        return firstWord;
    }

    private String getSecondWord(String input) {
        String[] arr = input.split(" ", 2);
        String secondWord = arr[1].strip();
        return secondWord;
    }

    private Keyword getKeyword(String input) {
        Keyword keyword = Keyword.valueOf(getFirstWord(input).toUpperCase());
        return keyword;
    }

    private String[] getDetails(String input) throws InvalidTimeException {
        String[] result = new String[2];
        String[] split = input.split("\\(");

        if (getSecondWord(split[1]).length() < 1) {
            throw new InvalidTimeException("☹ OOPS!!! Please enter a suitable timing!");
        }
        result[0] = getSecondWord(split[0]);
        result[1] = getSecondWord(split[1]).substring(0, getSecondWord(split[1]).length() - 1);
        return result;
    }

    /**
     * Parses the command based on the user's input to execute the corresponding action.
     * Returns a String which is Duke's reply to the user's input.
     *
     * @return a String that corresponds to the user's input.
     */
    public String parseCommand(String input) {

        try {
            Keyword keyword = getKeyword(input);

            switch (keyword) {
            case HELP:
                return ui.showSupportedCommands();
            case BYE:
                return "Bye!";
            case LIST:
                return tasks.printTaskList(tasks);
            case DONE:
                return tasks.markTaskDone(input);
            case DELETE:
                return tasks.deleteTask(input);
            case TODO:
                if (input.substring(5).length() < 1) {
                    throw new InvalidDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                return tasks.addToDo(input.substring(5));
            case DEADLINE:
                String[] deadlineDetails = getDetails(input);
                return tasks.addDeadline(deadlineDetails[0], deadlineDetails[1]);
            case EVENT:
                String[] eventDetails = getDetails(input);
                return tasks.addEvent(eventDetails[0], eventDetails[1]);
            case FIND:
                if (getSecondWord(input).length() < 1) {
                    throw new InvalidDescriptionException("☹ OOPS!!! Please enter a suitable keyword to find!");
                }
                String findKeyword = getSecondWord(input);
                return tasks.printTasksWithKeyword(findKeyword);
            default:
                return null;
            }
        } catch (InvalidTimeException | InvalidDescriptionException | InvalidInputException e) {
            return e.getMessage();
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            return "☹ OOPS!!! Please include an appropriate description/time!";
        } catch (IllegalArgumentException e) {
            return "☹ OOPS!!! Unable to recognise command, please try again!";
        }
    }
}
