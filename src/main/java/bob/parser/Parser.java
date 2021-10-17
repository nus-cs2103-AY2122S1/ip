package bob.parser;

import java.util.Objects;

import bob.Storage;
import bob.TaskList;
import bob.exception.BobException;
import bob.exception.InvalidDateException;
import bob.gui.Ui;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

/**
 * Represents an object that deals with making sense of the user commands.
 */
public class Parser {
    private boolean isListCommand;
    private boolean isHelpCommand;
    private boolean isDoneCommand;
    private boolean isDeleteCommand;
    private boolean isTodoCommand;
    private boolean isDeadlineCommand;
    private boolean isEventCommand;
    private boolean isSearchCommand;

    public Parser() {}

    /**
     * Sets the correct boolean field of the parser to true to indicate the user command type. All the other boolean
     * fields are set to false.
     *
     * @param input User input.
     */
    private void setCommandType(String input) {
        this.isListCommand = Objects.equals(input, "list");
        this.isHelpCommand = Objects.equals(input, "help");
        this.isDoneCommand = input.matches("done(.*)");
        this.isDeleteCommand = input.matches("delete(.*)");
        this.isTodoCommand = input.matches("todo(.*)");
        this.isDeadlineCommand = input.matches("deadline(.*)");
        this.isEventCommand = input.matches("event(.*)");
        this.isSearchCommand = input.matches("find(.*)");
    }

    /**
     * Makes sense of the user command and returns the appropriate Bob response. Includes the handling of invalid
     * user inputs.
     *
     * @param input User input.
     * @param ui Ui object responsible for interactions with the user.
     * @param tasks Tasklist that stores the user's list of tasks.
     * @param storage Storage object responsible for loading tasks from the file and saving tasks into the file.
     * @return The appropriate Bob response to the user command.
     */
    public String getResponse(String input, Ui ui, TaskList tasks, Storage storage) {
        try {
            setCommandType(input);
            ValidInputChecker checker = new ValidInputChecker(isListCommand, isHelpCommand, isDoneCommand,
                    isDeleteCommand, isTodoCommand, isDeadlineCommand, isEventCommand, isSearchCommand, input, tasks);
            checker.checkInput();

            return getValidResponse(input, ui, tasks, storage);

        } catch (BobException e) {
            return ui.getErrorMessage(e);
        }
    }

    /**
     * Interprets the valid user command and returns the appropriate Bob response.
     * @param input User input.
     * @param ui Ui object responsible for interactions with the user.
     * @param tasks Tasklist that stores the user's list of tasks.
     * @param storage Storage object responsible for loading tasks from the file and saving tasks into the file.
     * @return The appropriate Bob response to the user command.
     * @throws InvalidDateException If the user provides the date of the Deadline or Event task in the wrong format.
     */
    private String getValidResponse(String input, Ui ui, TaskList tasks, Storage storage) throws InvalidDateException {
        if (isListCommand) {
            return ui.getListMessage(tasks);
        } else if (isHelpCommand) {
            return ui.getHelpMessage();
        } else if (isDoneCommand) {
            return getDoneResponse(input, ui, tasks, storage);
        } else if (isDeleteCommand) {
            return getDeleteResponse(input, ui, tasks, storage);
        } else if (isTodoCommand || isDeadlineCommand || isEventCommand) {
            return getTaskResponse(input, ui, tasks, storage);
        } else if (isSearchCommand) {
            String[] splitResponse = input.split(" ", 2);
            return ui.getSearchResultMessage(splitResponse[1], tasks);
        } else { // Should not reach this line.
            return "";
        }
    }

    /**
     * Interprets the valid user Done command and returns the appropriate Bob response.
     *
     * @param input User input.
     * @param ui Ui object responsible for interactions with the user.
     * @param tasks Tasklist that stores the user's list of tasks.
     * @param storage Storage object responsible for loading tasks from the file and saving tasks into the file.
     * @return The appropriate Bob response to the user Done command.
     */
    private String getDoneResponse(String input, Ui ui, TaskList tasks, Storage storage) {
        String response;
        String[] splitResponse = input.split(" ", 2);
        int index = Integer.parseInt(splitResponse[1]) - 1;
        response = ui.getIndexCompletedMessage(index, tasks);
        storage.updateBobFile(tasks);
        return response;
    }

    /**
     * Interprets the valid user Delete command and returns the appropriate Bob response.
     *
     * @param input User input.
     * @param ui Ui object responsible for interactions with the user.
     * @param tasks Tasklist that stores the user's list of tasks.
     * @param storage Storage object responsible for loading tasks from the file and saving tasks into the file.
     * @return The appropriate Bob response to the user Delete command.
     */
    private String getDeleteResponse(String input, Ui ui, TaskList tasks, Storage storage) {
        String response;
        String[] splitResponse = input.split(" ", 2);
        int index = Integer.parseInt(splitResponse[1]) - 1;
        response = ui.getIndexDeletedMessage(index, tasks);
        storage.updateBobFile(tasks);
        return response;
    }

    /**
     * Interprets the valid user Task commands and returns the appropriate Bob response.
     *
     * @param input User input.
     * @param ui Ui object responsible for interactions with the user.
     * @param tasks Tasklist that stores the user's list of tasks.
     * @param storage Storage object responsible for loading tasks from the file and saving tasks into the file.
     * @return The appropriate Bob response to the user Task command.
     * @throws InvalidDateException If the user provides the date of the Deadline or Event task in the wrong format.
     */
    private String getTaskResponse(String input, Ui ui, TaskList tasks, Storage storage) throws InvalidDateException {
        String response;
        String[] splitResponse = input.split(" ", 2);
        Task newTask = getNewTask(splitResponse);
        response = ui.getTaskAddedMessage(newTask, tasks);
        storage.updateBobFile(tasks);
        return response;
    }

    /**
     * Gets the new Task to be added to the list of tasks based on the user command.
     *
     * @param splitResponse The section of the command string that contains the details of the Task to be added.
     * @return The task to be added to the list of tasks.
     * @throws InvalidDateException If the user provides the date of the Deadline or Event task in the wrong format.
     */
    private Task getNewTask(String[] splitResponse) throws InvalidDateException {
        Task newTask;
        if (isTodoCommand) {
            newTask = new Todo(splitResponse[1]);
        } else if (isDeadlineCommand) {
            String[] splitAgain = splitResponse[1].split(" /by ", 2);
            newTask = new Deadline(splitAgain[0], splitAgain[1]);
        } else if (isEventCommand) {
            String[] splitAgain = splitResponse[1].split(" /at ", 2);
            newTask = new Event(splitAgain[0], splitAgain[1]);
        } else { // Should never reach this branch.
            newTask = new Task("");
        }
        return newTask;
    }
}
