package bob;

import java.util.Objects;

import bob.exception.BobException;
import bob.exception.InvalidDateException;
import bob.exception.InvalidInputException;
import bob.exception.NoDeadlineException;
import bob.exception.NoEventTimingException;
import bob.exception.NoKeywordException;
import bob.exception.NoTaskAndDateException;
import bob.exception.NoTaskException;
import bob.exception.NotFoundException;
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
            checkInput(input, tasks);

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

    /**
     * Checks the validity of the user input to determine if any exception needs to be thrown.
     *
     * @param response The user input.
     * @param tasklist Current list of tasks.
     * @throws InvalidInputException If the user input is not one of the supported commands.
     * @throws NoDeadlineException If the user does not specify the deadline of their Deadline task.
     * @throws NoEventTimingException If the user does not specify the timing of their Event task.
     * @throws NoKeywordException If the user does not specify the keyword in their task search.
     * @throws NoTaskAndDateException If the user does not specify both the task description and deadline or timing.
     * @throws NoTaskException If the user does not specify the task description.
     * @throws NotFoundException If the user tries to mark as completed or remove a task not inside the task list.
     */
    private void checkInput(String response, TaskList tasklist) throws InvalidInputException, NoTaskException,
            NoDeadlineException, NoEventTimingException, NotFoundException, NoKeywordException,
            NoTaskAndDateException {
        if (isListCommand || isHelpCommand) {
            // Correct input, do nothing
        } else if (isDoneCommand || isDeleteCommand) {
            checkDoneOrDelete(response, tasklist);
        } else if (isTodoCommand) {
            checkTodoCommand(response);
        } else if (isDeadlineCommand) {
            checkDeadlineCommand(response);
        } else if (isEventCommand) {
            checkEventCommand(response);
        } else if (isSearchCommand) {
            checkSearchCommand(response);
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Checks the validity of the user Done or Delete command to determine if any exception needs to be thrown.
     *
     * @param response The user input.
     * @param tasklist Current list of tasks.
     * @throws NotFoundException If the user tries to mark as completed or remove a task not inside the task list.
     */
    private void checkDoneOrDelete(String response, TaskList tasklist) throws NotFoundException {
        String[] splitResponse = response.split(" ", 2);
        boolean isIndexLessThanOrEqualToZero = Integer.parseInt(splitResponse[1]) <= 0;
        boolean isIndexGreaterThanListLength = Integer.parseInt(splitResponse[1])
                > Integer.parseInt(tasklist.getNoOfTasks());
        if (isIndexLessThanOrEqualToZero || isIndexGreaterThanListLength) {
            throw new NotFoundException();
        }
    }

    /**
     * Checks the validity of the user Todo command to determine if any exception needs to be thrown.
     *
     * @param response The user input.
     * @throws NoTaskException If the user does not specify the task description.
     */
    private void checkTodoCommand(String response) throws NoTaskException {
        String[] splitResponse = response.split(" ", 2);
        boolean hasNoTaskDescription = splitResponse.length == 1;
        if (hasNoTaskDescription) {
            throw new NoTaskException();
        }
    }

    /**
     * Checks the validity of the user Deadline command to determine if any exception needs to be thrown.
     *
     * @param response The user input.
     * @throws NoTaskAndDateException If the user does not specify both the task description and deadline or timing.
     * @throws NoTaskException If the user does not specify the task description.
     * @throws NoDeadlineException If the user does not specify the deadline of their Deadline task.
     */
    private void checkDeadlineCommand(String response) throws NoTaskAndDateException, NoTaskException,
            NoDeadlineException {
        String[] splitResponse = response.split(" ", 2);
        boolean hasNoTaskDescription = splitResponse.length == 1;
        boolean hasNoDate = !response.contains("/by");
        if (!hasNoDate && !hasNoTaskDescription) {
            hasNoTaskDescription = response.split("/by", 2)[0].split(" ", 0).length == 1;
        }
        if (!hasNoDate) {
            hasNoDate = response.split("/by", 0).length == 1;
        }
        boolean hasNoTaskAndDate = hasNoTaskDescription && hasNoDate;

        if (hasNoTaskAndDate) {
            throw new NoTaskAndDateException();
        } else if (hasNoTaskDescription) {
            throw new NoTaskException();
        } else if (hasNoDate) {
            throw new NoDeadlineException();
        }
    }

    /**
     * Checks the validity of the user Event command to determine if any exception needs to be thrown.
     *
     * @param response The user input.
     * @throws NoTaskAndDateException If the user does not specify both the task description and deadline or timing.
     * @throws NoTaskException If the user does not specify the task description.
     * @throws NoEventTimingException If the user does not specify the timing of their Event task.
     */
    private void checkEventCommand(String response) throws NoTaskAndDateException, NoTaskException,
            NoEventTimingException {
        String[] splitResponse = response.split(" ", 2);
        boolean hasNoTaskDescription = splitResponse.length == 1;
        boolean hasNoDate = !response.contains("/at");
        if (!hasNoDate && !hasNoTaskDescription) {
            hasNoTaskDescription = response.split("/at", 2)[0].split(" ", 0).length == 1;
        }
        if (!hasNoDate) {
            hasNoDate = response.split("/at", 0).length == 1;
        }
        boolean hasNoTaskAndDate = hasNoTaskDescription && hasNoDate;

        if (hasNoTaskAndDate) {
            throw new NoTaskAndDateException();
        } else if (hasNoTaskDescription) {
            throw new NoTaskException();
        } else if (hasNoDate) {
            throw new NoEventTimingException();
        }
    }

    /**
     * Checks the validity of the user Search command to determine if any exception needs to be thrown.
     *
     * @param response The user input.
     * @throws NoKeywordException If the user does not specify the keyword in their task search.
     */
    private void checkSearchCommand(String response) throws NoKeywordException {
        String[] splitResponse = response.split(" ", 2);
        boolean hasNoSearchKeyword = splitResponse.length == 1;
        if (hasNoSearchKeyword) {
            throw new NoKeywordException();
        }
    }
}
