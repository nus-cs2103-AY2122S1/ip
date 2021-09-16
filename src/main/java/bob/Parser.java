package bob;

import java.util.Objects;

import bob.exception.InvalidDateException;
import bob.exception.InvalidInputException;
import bob.exception.NoDeadlineException;
import bob.exception.NoEventTimingException;
import bob.exception.NoKeywordException;
import bob.exception.NoTaskAndDateException;
import bob.exception.NoTaskException;
import bob.exception.OutOfBoundsException;
import bob.gui.Ui;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

/**
 * Represents an object that deals with making sense of the user commands.
 */
public class Parser {
    /**
     * Constructor for creating a new Parser instance.
     */
    public Parser() {}

    /**
     * Makes sense of the user commands and returns the appropriate Bob response.
     *
     * @param input User input.
     * @param ui Ui object responsible for interactions with the user.
     * @param tasks Tasklist that stores the user's list of tasks.
     * @param storage Storage object responsible for loading tasks from the file and saving tasks into the file.
     */
    public String getResponse(String input, Ui ui, TaskList tasks, Storage storage) {
        try {
            checkInput(input, tasks);
            String response = "";
            assert (Objects.equals(input, "list") || input.matches("done(.*)")
                    || input.matches("delete(.*)") || input.matches("todo(.*)")
                    || input.matches("deadline(.*)") || input.matches("event(.*)")
                    || input.matches("find(.*)"));
            boolean isListCommand = Objects.equals(input, "list");
            boolean isHelpCommand = Objects.equals(input, "help");
            boolean isDoneCommand = input.matches("done(.*)");
            boolean isDeleteCommand = input.matches("delete(.*)");
            boolean isTodoCommand = input.matches("todo(.*)");
            boolean isDeadlineCommand = input.matches("deadline(.*)");
            boolean isEventCommand = input.matches("event(.*)");
            boolean isSearchCommand = input.matches("find(.*)");

            if (isListCommand) {
                return ui.getListMessage(tasks);
            } else if (isHelpCommand) {
                return ui.getHelpMessage();
            } else if (isDoneCommand) {
                String[] splitResponse = input.split(" ", 2);
                int index = Integer.parseInt(splitResponse[1]) - 1;
                response = ui.getIndexCompletedMessage(index, tasks);
                storage.updateBobFile(tasks);
            } else if (isDeleteCommand) {
                String[] splitResponse = input.split(" ", 2);
                int index = Integer.parseInt(splitResponse[1]) - 1;
                response = ui.getIndexDeletedMessage(index, tasks);
                storage.updateBobFile(tasks);
            } else if (isTodoCommand || isDeadlineCommand || isEventCommand) {
                String[] splitResponse = input.split(" ", 2);
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
                response = ui.getTaskAddedMessage(newTask, tasks);
                storage.updateBobFile(tasks);
            } else if (isSearchCommand) {
                String[] splitResponse = input.split(" ", 2);
                response = ui.getSearchResultMessage(splitResponse[1], tasks);
            }
            return response;

        } catch (InvalidDateException e) {
            return ui.getInvalidDateExceptionMessage();
        } catch (InvalidInputException e) {
            return ui.getInvalidInputExceptionMessage();
        } catch (NoDeadlineException e) {
            return ui.getNoDeadlineExceptionMessage();
        } catch (NoEventTimingException e) {
            return ui.getNoEventTimingExceptionMessage();
        } catch (NoKeywordException e) {
            return ui.getNoKeywordExceptionMessage();
        } catch (NoTaskAndDateException e) {
            return ui.getNoTaskAndDateExceptionMessage();
        } catch (NoTaskException e) {
            return ui.getNoTaskExceptionMessage();
        } catch (OutOfBoundsException e) {
            return ui.getOutOfBoundsExceptionMessage();
        }
    }

    /**
     * Checks the user input to determine if any exception needs to be thrown.
     *
     * @param response The user input.
     * @param tasklist Current list of tasks.
     * @throws InvalidInputException If the user input is not one of the supported commands.
     * @throws NoDeadlineException If the user does not specify the deadline of their Deadline task.
     * @throws NoEventTimingException If the user does not specify the timing of their Event task.
     * @throws NoKeywordException If the user does not specify the keyword in their task search.
     * @throws NoTaskAndDateException If the user does not specify both the task description and deadline or timing.
     * @throws NoTaskException If the user does not specify the task description.
     * @throws OutOfBoundsException If the user tries to mark as completed or remove a task not inside the task list.
     */
    private void checkInput(String response, TaskList tasklist) throws InvalidInputException, NoTaskException,
            NoDeadlineException, NoEventTimingException, OutOfBoundsException, NoKeywordException,
            NoTaskAndDateException {
        boolean isListCommand = Objects.equals(response, "list");
        boolean isHelpCommand = Objects.equals(response, "help");
        boolean isDoneCommand = response.matches("done(.*)");
        boolean isDeleteCommand = response.matches("delete(.*)");
        boolean isTodoCommand = response.matches("todo(.*)");
        boolean isDeadlineCommand = response.matches("deadline(.*)");
        boolean isEventCommand = response.matches("event(.*)");
        boolean isSearchCommand = response.matches("find(.*)");

        if (isListCommand || isHelpCommand) {
            // Correct input, do nothing
        } else if (isDoneCommand || isDeleteCommand) {
            String[] splitResponse = response.split(" ", 2);
            boolean isIndexLessThanOrEqualToZero = Integer.parseInt(splitResponse[1]) <= 0;
            boolean isIndexGreaterThanListLength = Integer.parseInt(splitResponse[1])
                    > Integer.parseInt(tasklist.getNoOfTasks());
            if (isIndexLessThanOrEqualToZero || isIndexGreaterThanListLength) {
                throw new OutOfBoundsException();
            }
        } else if (isTodoCommand) {
            String[] splitResponse = response.split(" ", 2);
            boolean hasNoTaskDescription = splitResponse.length == 1;
            if (hasNoTaskDescription) {
                throw new NoTaskException();
            }
        } else if (isDeadlineCommand) {
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
        } else if (isEventCommand) {
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
        } else if (isSearchCommand) {
            String[] splitResponse = response.split(" ", 2);
            boolean hasNoSearchKeyword = splitResponse.length == 1;
            if (hasNoSearchKeyword) {
                throw new NoKeywordException();
            }
        } else {
            throw new InvalidInputException();
        }
    }
}
