package bob;

import bob.exception.InvalidInputException;
import bob.exception.NoDeadlineException;
import bob.exception.NoEventTimingException;
import bob.exception.NoKeywordException;
import bob.exception.NoTaskException;
import bob.exception.OutOfBoundsException;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

import java.util.Objects;
import java.util.Scanner;

/**
 * Represents an object that deals with making sense of the user commands.
 */
public class Parser {
    /**
     * Constructor for creating a new Parser instance.
     */
    public Parser() {}

    /**
     * Begins the "chatting" sequence, making sense of the user commands.
     *
     * @param ui Ui object responsible for interactions with the user.
     * @param tasks Tasklist that stores the user's list of tasks.
     * @param storage Storage object responsible for loading tasks from the file and saving tasks into the file.
     */
    public void run(Ui ui, TaskList tasks, Storage storage) {
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        while (!Objects.equals(response, "bye")) {
            try {
                checkInput(response, tasks);

                if (Objects.equals(response, "list")) {
                    ui.showList(tasks);
                    response = scanner.nextLine();
                } else if (response.matches("done(.*)")) {
                    String[] splitResponse = response.split(" ", 2);
                    ui.showIndexCompleted(Integer.parseInt(splitResponse[1]) - 1, tasks);
                    storage.updateBobFile(tasks);
                    response = scanner.nextLine();
                } else if (response.matches("delete(.*)")) {
                    String[] splitResponse = response.split(" ", 2);
                    ui.showIndexDeleted(Integer.parseInt(splitResponse[1]) - 1, tasks);
                    storage.updateBobFile(tasks);
                    response = scanner.nextLine();
                } else if (response.matches("todo(.*)") || response.matches("deadline(.*)")
                        || response.matches("event(.*)")) {
                    String[] splitResponse = response.split(" ", 2);
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
                    ui.showTaskAdded(newTask, tasks);
                    storage.updateBobFile(tasks);
                    response = scanner.nextLine();
                } else if (response.matches("find(.*)")) {
                    String[] splitResponse = response.split(" ", 2);
                    ui.showSearchResult(splitResponse[1], tasks);
                    response = scanner.nextLine();
                }
            } catch (InvalidInputException e) {
                ui.showInvalidInputException();
                response = scanner.nextLine();
            } catch (NoTaskException e) {
                ui.showNoTaskException();
                response = scanner.nextLine();
            } catch (NoDeadlineException e) {
                ui.showNoDeadlineException();
                response = scanner.nextLine();
            } catch (NoEventTimingException e) {
                ui.showNoEventTimingException();
                response = scanner.nextLine();
            } catch (NoKeywordException e) {
                ui.showNoKeywordException();
                response = scanner.nextLine();
            } catch (OutOfBoundsException e) {
                ui.showOutOfBoundsException();
                response = scanner.nextLine();
            }
        }
    }

    /**
     * Checks the user input to determine if any exception needs to be thrown.
     *
     * @param response The user input.
     * @param tasklist Current list of tasks.
     * @throws InvalidInputException If the user input is not one of the supported commands.
     * @throws NoTaskException If the user does not specify the task description.
     * @throws NoDeadlineException If the user does not specify the deadline of their Deadline task.
     * @throws NoEventTimingException If the user does not specify the timing of their Event task.
     * @throws OutOfBoundsException If the user tries to mark as completed or remove a task not inside the task list.
     * @throws NoKeywordException If the user does not specify the keyword in their task search.
     */
    private void checkInput(String response, TaskList tasklist) throws InvalidInputException, NoTaskException,
            NoDeadlineException, NoEventTimingException, OutOfBoundsException, NoKeywordException {
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
            }
        } else if (response.matches("event(.*)")) {
            String[] splitResponse = response.split(" ", 2);
            if (splitResponse.length == 1) {
                throw new NoTaskException();
            } else if (!response.contains("/at")) {
                throw new NoEventTimingException();
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
