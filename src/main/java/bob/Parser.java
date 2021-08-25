package bob;

import bob.exception.InvalidInputException;
import bob.exception.NoDeadlineException;
import bob.exception.NoEventTimingException;
import bob.exception.NoTaskException;
import bob.exception.OutOfBoundsException;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

import java.util.Objects;
import java.util.Scanner;

public class Parser {
    public Parser() {}

    public void run(Ui ui, TaskList tasks, Storage storage) {
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        while (!Objects.equals(response, "bye")) {
            try {
                checkInput(response, tasks);

                if (Objects.equals(response, "list")) { //show list of tasks
                    ui.showList(tasks);
                    response = scanner.nextLine();
                } else if (response.matches("done(.*)")) { //complete a task
                    String[] splitResponse = response.split(" ", 2);
                    ui.showIndexCompleted(Integer.parseInt(splitResponse[1]) - 1, tasks);
                    storage.updateBobFile(tasks);
                    response = scanner.nextLine();
                } else if (response.matches("delete(.*)")) {
                    String[] splitResponse = response.split(" ", 2);
                    ui.showIndexDeleted(Integer.parseInt(splitResponse[1]) - 1, tasks);
                    storage.updateBobFile(tasks);
                    response = scanner.nextLine();
                } else if (response.matches("todo(.*)") || response.matches("deadline(.*)") //add new task
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
            } catch (OutOfBoundsException e) {
                ui.showOutOfBoundsException();
                response = scanner.nextLine();
            }
        }
    }

    private void checkInput(String response, TaskList tasklist) throws InvalidInputException, NoTaskException,
            NoDeadlineException, NoEventTimingException, OutOfBoundsException {
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
        } else {
            throw new InvalidInputException();
        }
    }
}
