package bob.parser;

import bob.TaskList;
import bob.exception.InvalidInputException;
import bob.exception.NoDeadlineException;
import bob.exception.NoEventTimingException;
import bob.exception.NoKeywordException;
import bob.exception.NoTaskAndDateException;
import bob.exception.NoTaskException;
import bob.exception.NotFoundException;

public class ValidInputChecker {
    private boolean isListCommand;
    private boolean isHelpCommand;
    private boolean isDoneCommand;
    private boolean isDeleteCommand;
    private boolean isTodoCommand;
    private boolean isDeadlineCommand;
    private boolean isEventCommand;
    private boolean isSearchCommand;

    /**
     * The user input.
     */
    private String response;

    /**
     * The current list of tasks in the user's list.
     */
    private TaskList tasklist;

    /**
     * Constructs a new ValidInputChecker instance.
     *
     * @param isListCommand True if the user command is a List command.
     * @param isHelpCommand True if the user command is a Help command.
     * @param isDoneCommand True if the user command is a Done command.
     * @param isDeleteCommand True if the user command is a Delete command.
     * @param isTodoCommand True if the user command is a Todo command.
     * @param isDeadlineCommand True if the user command is a Deadline command.
     * @param isEventCommand True if the user command is a Event command.
     * @param isSearchCommand True if the user command is a Search command.
     * @param response The user input.
     * @param tasklist The current list of tasks in the user's list.
     */
    public ValidInputChecker(boolean isListCommand, boolean isHelpCommand, boolean isDoneCommand,
                             boolean isDeleteCommand, boolean isTodoCommand, boolean isDeadlineCommand,
                             boolean isEventCommand, boolean isSearchCommand, String response, TaskList tasklist) {
        this.isListCommand = isListCommand;
        this.isHelpCommand = isHelpCommand;
        this.isDoneCommand = isDoneCommand;
        this.isDeleteCommand = isDeleteCommand;
        this.isTodoCommand = isTodoCommand;
        this.isDeadlineCommand = isDeadlineCommand;
        this.isEventCommand = isEventCommand;
        this.isSearchCommand = isSearchCommand;
        this.response = response;
        this.tasklist = tasklist;
    }

    /**
     * Checks the validity of the user input to determine if any exception needs to be thrown.
     *
     * @throws InvalidInputException If the user input is not one of the supported commands.
     * @throws NoDeadlineException If the user does not specify the deadline of their Deadline task.
     * @throws NoEventTimingException If the user does not specify the timing of their Event task.
     * @throws NoKeywordException If the user does not specify the keyword in their task search.
     * @throws NoTaskAndDateException If the user does not specify both the task description and deadline or timing.
     * @throws NoTaskException If the user does not specify the task description.
     * @throws NotFoundException If the user tries to mark as completed or remove a task not inside the task list.
     */
    public void checkInput() throws InvalidInputException, NoTaskException,
            NoDeadlineException, NoEventTimingException, NotFoundException, NoKeywordException,
            NoTaskAndDateException {
        if (isListCommand || isHelpCommand) {
            // Correct input, do nothing
        } else if (isDoneCommand || isDeleteCommand) {
            checkDoneOrDelete();
        } else if (isTodoCommand) {
            checkTodoCommand();
        } else if (isDeadlineCommand) {
            checkDeadlineCommand();
        } else if (isEventCommand) {
            checkEventCommand();
        } else if (isSearchCommand) {
            checkSearchCommand();
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Checks the validity of the user Done or Delete command to determine if any exception needs to be thrown.
     *
     * @throws NotFoundException If the user tries to mark as completed or remove a task not inside the task list.
     */
    private void checkDoneOrDelete() throws NotFoundException {
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
     * @throws NoTaskException If the user does not specify the task description.
     */
    private void checkTodoCommand() throws NoTaskException {
        String[] splitResponse = response.split(" ", 2);
        boolean hasNoTaskDescription = splitResponse.length == 1;
        if (hasNoTaskDescription) {
            throw new NoTaskException();
        }
    }

    /**
     * Checks the validity of the user Deadline command to determine if any exception needs to be thrown.
     *
     * @throws NoTaskAndDateException If the user does not specify both the task description and deadline or timing.
     * @throws NoTaskException If the user does not specify the task description.
     * @throws NoDeadlineException If the user does not specify the deadline of their Deadline task.
     */
    private void checkDeadlineCommand() throws NoTaskAndDateException, NoTaskException,
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
     * @throws NoTaskAndDateException If the user does not specify both the task description and deadline or timing.
     * @throws NoTaskException If the user does not specify the task description.
     * @throws NoEventTimingException If the user does not specify the timing of their Event task.
     */
    private void checkEventCommand() throws NoTaskAndDateException, NoTaskException,
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
     * @throws NoKeywordException If the user does not specify the keyword in their task search.
     */
    private void checkSearchCommand() throws NoKeywordException {
        String[] splitResponse = response.split(" ", 2);
        boolean hasNoSearchKeyword = splitResponse.length == 1;
        if (hasNoSearchKeyword) {
            throw new NoKeywordException();
        }
    }
}
