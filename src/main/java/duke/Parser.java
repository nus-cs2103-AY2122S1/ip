package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.MarkDoneCommand;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.MissingDateException;
import duke.exceptions.TaskOutOfRangeException;

/**
 * Deals with making sense of the user command to Duck Chatbot.
 */
public class Parser {

    /**
     * Returns Command called by the user.
     *
     * @param fullCommand Entire line of user input from scanner.
     * @return Command object based on the command word used by user.
     * @throws DukeException If task description is empty, user input for date or task number is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String commandWord = fullCommand;
        String taskDescription = null;
        Command command = null;
        if (fullCommand.contains(" ")) {
            String[] commandAndDescription = fullCommand.split(" ", 2);
            commandWord = commandAndDescription[0];
            taskDescription = commandAndDescription[1];
            assert taskDescription != null;
        }
        try {
            switch (commandWord) {
            case "bye":
                command = new ExitCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "done":
                if (taskDescription == null) {
                    throw new TaskOutOfRangeException();
                }
                int taskNo = Integer.parseInt(taskDescription);
                command = new MarkDoneCommand(taskNo);
                break;
            case "delete":
                if (taskDescription == null) {
                    throw new TaskOutOfRangeException();
                }
                taskNo = Integer.parseInt(taskDescription);
                command = new DeleteCommand(taskNo);
                break;
            case "todo":
                if (taskDescription == null) {
                    throw new EmptyDescriptionException();
                }
                command = new AddCommand(TaskType.TODO, new String[]{taskDescription});
                break;
            case "deadline":
                if (taskDescription == null) {
                    throw new EmptyDescriptionException();
                }
                String[] descriptionDate;
                if (taskDescription.contains("/")) {
                    descriptionDate = taskDescription.split(" /by ");
                } else {
                    throw new MissingDateException();
                }
                command = new AddCommand(TaskType.DEADLINE, descriptionDate);
                break;
            case "event":
                if (taskDescription == null) {
                    throw new EmptyDescriptionException();
                }
                if (taskDescription.contains("/")) {
                    descriptionDate = taskDescription.split(" /at ");
                } else {
                    throw new MissingDateException();
                }
                command = new AddCommand(TaskType.EVENT, descriptionDate);
                break;
            case "find":
                if (taskDescription == null) {
                    throw new EmptyDescriptionException();
                }
                command = new FindCommand(taskDescription);
                break;
            case "help":
                command = new HelpCommand();
                break;
            default:
                throw new InvalidInputException();
            }
        } catch (DateTimeParseException e) {
            System.out.println("OOPS! Please input date in this format: yyyy-mm-dd");
        }
        assert command != null;
        return command;
    }

    /**
     * Parses search string to obtain keywords for searching through tasklist.
     * @param searchString Search description from user input.
     * @return Array of search words from broken down searchString. 
     */
    public static ArrayList<String> parseSearchString(String searchString) {
        ArrayList<String> searchTerms = new ArrayList<>();
        searchTerms.add(searchString);
        if (searchString.contains(" ")) {
            for (String word : searchString.split(" ")) {
                searchTerms.add(word);
            }
        }
        return searchTerms;
    }
}
