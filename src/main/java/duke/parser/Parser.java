package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.exception.EmptyTaskDescriptionException;
import duke.exception.MissingKeywordException;
import duke.exception.MissingTagDetailsException;
import duke.exception.MissingTaskIndexException;
import duke.exception.TimeNotSpecifiedException;
import duke.exception.UnrecognisedCommandException;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into a command for execution.
     *
     * @param command The user input string.
     * @return A command to be executed based on the user input.
     * @throws UnrecognisedCommandException If the entered command is not recognised.
     * @throws MissingTaskIndexException If the task index is not provided.
     * @throws EmptyTaskDescriptionException If the task description is not provided.
     * @throws TimeNotSpecifiedException If the date is not provided.
     * @throws DateTimeParseException If the date cannot be parsed into a LocalDate instance.
     * @throws MissingKeywordException If the keyword is missing.
     */
    public Command parse(String command) throws UnrecognisedCommandException, MissingTaskIndexException,
            EmptyTaskDescriptionException, TimeNotSpecifiedException, DateTimeParseException,
            MissingKeywordException, MissingTagDetailsException {
        // Splits the string on spaces
        String[] wordsArray = command.split(" ");
        String firstWord = wordsArray[0];

        // Acts accordingly based on the first word of the user input
        if (wordsArray.length == 0) {
            throw new UnrecognisedCommandException();
        } else if (firstWord.equals("list")) {
            return new ListCommand();
        } else if (firstWord.equals("done")) {
            return markTaskAsDone(command);
        } else if (firstWord.equals("delete")) {
            return deleteTask(command);
        } else if (firstWord.equals("find")) {
            return findTasks(command);
        } else if (firstWord.equals("tag")) {
            return tagTask(command);
        } else if (firstWord.equals("todo") || firstWord.equals("event") || firstWord.equals("deadline")) {
            return addTask(command);
        } else {
            throw new UnrecognisedCommandException();
        }
    }

    private DoneCommand markTaskAsDone(String userInput) throws MissingTaskIndexException {
        String[] wordsArray = userInput.split(" ");

        // Checks for extra user input, and if the second word is a number
        if (wordsArray.length != 2 || !wordsArray[1].matches("-?\\d+(\\.\\d+)?")) {
            throw new MissingTaskIndexException();
        }

        int taskId = Integer.valueOf(wordsArray[1]) - 1;
        return new DoneCommand(taskId);
    }

    private DeleteCommand deleteTask(String userInput) throws MissingTaskIndexException {
        String[] wordsArray = userInput.split(" ");

        // Checks for extra user input, and if the second word is a number
        if (wordsArray.length != 2 || !wordsArray[1].matches("-?\\d+(\\.\\d+)?")) {
            throw new MissingTaskIndexException();
        }

        int taskId = Integer.valueOf(wordsArray[1]) - 1;
        return new DeleteCommand(taskId);
    }

    private FindCommand findTasks(String userInput) throws MissingKeywordException {
        String[] wordsArray = userInput.split(" ");

        // Checks for extra user input
        if (wordsArray.length != 2) {
            throw new MissingKeywordException();
        }

        String searchTerm = wordsArray[1];
        return new FindCommand(searchTerm);
    }

    private TagCommand tagTask(String userInput) throws MissingTagDetailsException {
        String[] wordsArray = userInput.split(" ");

        // Checks if the task index and tag name are provided
        if (wordsArray.length != 3 || !wordsArray[1].matches("-?\\d+(\\.\\d+)?")
                || wordsArray[2].trim().isEmpty()) {
            throw new MissingTagDetailsException();
        }

        int taskId = Integer.valueOf(wordsArray[1]) - 1;
        String tagName = wordsArray[2];
        return new TagCommand(taskId, tagName);
    }

    private AddCommand addTask(String userInput) throws EmptyTaskDescriptionException,
            TimeNotSpecifiedException, DateTimeParseException {
        String[] wordsArray = userInput.split(" ");

        // Checks if a task description is provided by the user
        String taskType = wordsArray[0];
        int indexOfSpaceChar = userInput.indexOf(" ");
        if (indexOfSpaceChar == -1) {
            throw new EmptyTaskDescriptionException(taskType);
        }
        String taskDescription = userInput.substring(indexOfSpaceChar + 1);
        if (taskDescription.trim().isEmpty()) {
            throw new EmptyTaskDescriptionException(taskType);
        }

        // Parsing the user input based on the task type
        if (taskType.equals("deadline")) {
            return addDeadline(taskDescription);
        } else if (taskType.equals("event")) {
            return addEvent(taskDescription);
        } else {
            return addTodo(taskDescription);
        }
    }

    private AddCommand addDeadline(String taskDescription) throws TimeNotSpecifiedException,
            DateTimeParseException {
        if (!taskDescription.contains(" /by ")) {
            throw new TimeNotSpecifiedException(" /by ");
        }
        String[] descriptionArr = taskDescription.split(" /by ");
        String task = descriptionArr[0];
        String deadline = descriptionArr[1];
        if (deadline.trim().isEmpty()) {
            throw new TimeNotSpecifiedException(" /by ");
        }
        LocalDate date = LocalDate.parse(deadline);
        return new AddCommand("deadline", task, date);
    }

    private AddCommand addEvent(String taskDescription) throws TimeNotSpecifiedException,
            DateTimeParseException {
        if (!taskDescription.contains(" /at ")) {
            throw new TimeNotSpecifiedException(" /at ");
        }
        String[] descriptionArr = taskDescription.split(" /at ");
        String task = descriptionArr[0];
        String timeFrame = descriptionArr[1];
        if (timeFrame.trim().isEmpty()) {
            throw new TimeNotSpecifiedException(" /at ");
        }
        LocalDate date = LocalDate.parse(timeFrame);
        return new AddCommand("event", task, date);
    }

    private AddCommand addTodo(String taskDescription) {
        return new AddCommand("todo", taskDescription);
    }
}
