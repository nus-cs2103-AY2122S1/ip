package angrybot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import angrybot.command.AddCommand;
import angrybot.command.AngryBotCommand;
import angrybot.command.DeleteCommand;
import angrybot.command.DoneCommand;
import angrybot.command.ExitCommand;
import angrybot.command.FindCommand;
import angrybot.command.HelpCommand;
import angrybot.command.ListCommand;
import angrybot.command.SortCommand;
import angrybot.exception.AngryBotException;
import angrybot.exception.InvalidDateTimeException;
import angrybot.exception.NoCommandDescriptionException;
import angrybot.exception.NoDateTimeException;
import angrybot.exception.NoTaskDescriptionException;
import angrybot.exception.UnknownCommandException;
import angrybot.storage.Storage;
import angrybot.task.Deadline;
import angrybot.task.Event;
import angrybot.task.Task;
import angrybot.task.TaskList;
import angrybot.task.ToDo;
import angrybot.ui.Ui;



/**
 * Encapsulates the processing of user inputs to AngryBot.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class Parser {
    private final TaskList list;
    private final Storage storage;
    private final Ui ui;
    private final List<String> COMMAND_TYPES = Arrays.asList(new String[]{"list", "bye", "sort", "todo", "event", "deadline", "find", "help",
            "delete", "done" });

    /**
     * Creates a Parser that handles user input and turn it into respective
     * commands and execute the required functions that user demands.
     *
     * @param list    The TaskList handler that is handling the list of task.
     * @param storage The Storage handler that is in-charged of saving and loading files on local directory.
     * @param ui      The Ui handler that handles printing of output, if required.
     */
    public Parser(TaskList list, Storage storage, Ui ui) {
        this.list = list;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Returns the respective AngryBotCommand that user want AngryBot to do.
     * If the command is not supported
     *
     * @param userInput User's input on what they want AngryBot to do.
     * @return The respective command object.
     * @throws AngryBotException If some error occurred in the processing of user input,
     *                       like the user inputted some command that AngryBot does not support.
     */
    public AngryBotCommand processInput(String userInput) throws AngryBotException {
        String commandType = getCommandType(userInput).toLowerCase();
        String commandDescription = getCommandDescription(userInput, commandType);

        switch (commandType) {
        case "list":
            return new ListCommand(ui, storage, list);

        case "bye":
            return new ExitCommand(ui, storage, list);

        case "done":
            return new DoneCommand(ui, storage, list, getTaskNumber(commandDescription));

        case "todo": case "deadline": case "event":
            return new AddCommand(ui, storage, list, processTaskDescriptions(commandType, commandDescription));

        case "delete":
            return new DeleteCommand(ui, storage, list, getTaskNumber(commandDescription));

        case "find":
            return new FindCommand(ui, storage, list, commandDescription);

        case "sort":
            return new SortCommand(ui, storage, list, checkReverseSort(commandDescription));

        case "help":
            return new HelpCommand(ui, storage, list);

        default:
            throw new UnknownCommandException();
        }
    }

    private Task processTaskDescriptions(String taskType, String commandDescription) throws InvalidDateTimeException,
            NoDateTimeException, NoTaskDescriptionException {
        switch (taskType) {
        case "deadline": case "event":
            return createTaskWithDateTime(commandDescription, taskType);

        default:
            return new ToDo(commandDescription, false);
        }
    }

    private String getCommandType(String userInput) throws UnknownCommandException {
        String commandType =  userInput.split(" ")[0];
        if (COMMAND_TYPES.contains(commandType)) {
            return commandType;
        }
        throw new UnknownCommandException();
    }

    private Task createTaskWithDateTime(String commandDescription, String taskType) throws NoDateTimeException,
            InvalidDateTimeException, NoTaskDescriptionException {
        LocalDateTime dt = getDateTime(commandDescription, taskType);
        String taskDescription = getTaskDescription(commandDescription);
        if (taskType.equals("deadline")) {
            return new Deadline(taskDescription, false, dt);
        } else {
            return new Event(taskDescription, false, dt);
        }

    }

    private String getCommandDescription(String userInput, String taskType) throws NoCommandDescriptionException {
        int index = userInput.indexOf(" ");
        if (index < 0) {
            if (!(taskType.equals("list") || taskType.equals("bye") || taskType.equals("sort")
                    || taskType.equals("help"))) {
                throw new NoCommandDescriptionException();
            }
        }
        if (userInput.substring(index + 1).isBlank()) {
            throw new NoCommandDescriptionException();
        }
        return userInput.substring(index + 1);
    }

    private String getTaskDescription(String commandDescription) throws NoDateTimeException, NoTaskDescriptionException {
        int slashIndex = commandDescription.indexOf("/");
        if (slashIndex < 0) {
            throw new NoDateTimeException();
        }
        try {
            if (commandDescription.substring(0, slashIndex - 1).isBlank()) {
                throw new NoTaskDescriptionException();
            }
            return commandDescription.substring(0, slashIndex - 1);
        } catch (StringIndexOutOfBoundsException e){
            throw new NoTaskDescriptionException();
        }

    }

    private int getTaskNumber(String commandDescription) throws UnknownCommandException {
        try {
            return Integer.parseInt(commandDescription.split(" ")[0]);
        } catch (NumberFormatException e) {
            throw new UnknownCommandException();
        }
    }

    private LocalDateTime getDateTime(String taskDescription, String taskType) throws NoDateTimeException,
            InvalidDateTimeException {
        String indicator;
        if (taskType.equals("deadline")) {
            indicator = "/by";
        } else {
            indicator = "/at";
        }

        int index = taskDescription.indexOf(indicator);
        if (index >= 0) {
            try {
                return LocalDateTime.parse(taskDescription.substring(index + 4),
                        DateTimeFormatter.ofPattern("yyyy-M-d H:m"));
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException();
            }
        }

        throw new NoDateTimeException();
    }

    private boolean checkReverseSort(String commandDescription) {
        return commandDescription.toLowerCase().contains("reverse");
    }
}
