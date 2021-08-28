package bob;

import bob.exception.*;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

import java.util.Objects;

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

            if (Objects.equals(input, "list")) {
                return ui.getListMessage(tasks);
            } else if (input.matches("done(.*)")) {
                String[] splitResponse = input.split(" ", 2);
                storage.updateBobFile(tasks);
                return ui.getIndexCompletedMessage(Integer.parseInt(splitResponse[1]) - 1, tasks);
            } else if (input.matches("delete(.*)")) {
                String[] splitResponse = input.split(" ", 2);
                storage.updateBobFile(tasks);
                return ui.getIndexDeletedMessage(Integer.parseInt(splitResponse[1]) - 1, tasks);
            } else if (input.matches("todo(.*)") || input.matches("deadline(.*)")
                    || input.matches("event(.*)")) {
                String[] splitResponse = input.split(" ", 2);
                Task newTask;
                if (Objects.equals(splitResponse[0], "todo")) {
                    newTask = new Todo(splitResponse[1]);
                } else if (Objects.equals(splitResponse[0], "deadline")) {
                    String[] splitAgain = splitResponse[1].split(" /by ", 2);
                    newTask = new Deadline(splitAgain[0], splitAgain[1]);
                } else {
                    String[] splitAgain = splitResponse[1].split(" /at ", 2);
                    newTask = new Event(splitAgain[0], splitAgain[1]);
                }
                storage.updateBobFile(tasks);
                return ui.getTaskAddedMessage(newTask, tasks);
            } else if (input.matches("find(.*)")) {
                String[] splitResponse = input.split(" ", 2);
                return ui.getSearchResultMessage(splitResponse[1], tasks);
            }
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

        // Should never reach this line.
        return "";
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
            NoDeadlineException, NoEventTimingException, OutOfBoundsException, NoKeywordException, NoTaskAndDateException {
        if (Objects.equals(response, "list")) {
            // Correct input checker, do nothing
        } else if (response.matches("done(.*)") || response.matches("delete(.*)")) {
            String[] splitResponse = response.split(" ", 2);
            if (Integer.parseInt(splitResponse[1]) <= 0
                    || Integer.parseInt(splitResponse[1]) > Integer.parseInt(tasklist.getNoOfTasks())) {
                throw new OutOfBoundsException();
            }
        } else if (response.matches("todo(.*)")) {
            String[] splitResponse = response.split(" ", 2);
            if (splitResponse.length == 1) {
                throw new NoTaskException();
            }
        } else if (response.matches("deadline(.*)")) {
            String[] splitResponse = response.split(" ", 2);
            if (splitResponse.length == 1) {
                throw new NoTaskException();
            } else if (!response.contains("/by")) {
                throw new NoDeadlineException();
            } else if (splitResponse.length == 2) {
                throw new NoTaskAndDateException();
            } else if (splitResponse[1].split(" ", 3).length == 2){
                throw new NoTaskException();
            }
        } else if (response.matches("event(.*)")) {
            String[] splitResponse = response.split(" ", 2);
            if (splitResponse.length == 1) {
                throw new NoTaskException();
            } else if (!response.contains("/at")) {
                throw new NoEventTimingException();
            } else if (splitResponse.length == 2) {
                throw new NoTaskAndDateException();
            } else if (splitResponse[1].split(" ", 3).length == 2){
                throw new NoTaskException();
            }
        } else if (response.matches("find(.*)")) {
            String[] splitResponse = response.split(" ", 2);
            if (splitResponse.length == 1) {
                throw new NoKeywordException();
            }
        } else {
            throw new InvalidInputException();
        }
    }
}
