package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that handles adding tasks to the TaskList.
 */
public class AddCommand implements Command {
    private String userInput;

    /**
     * Constructor for AddCommand object.
     *
     * @param userInput input form user
     */
    public AddCommand(String userInput) {
        super();
        this.userInput = userInput;
    }

    /**
     * Adds a "to do" task to the TaskList and returns a response.
     *
     * @param s name of the task
     * @param tasks the Tasklist where the task will be added
     * @param ui the Ui
     * @return the corresponding response upon successfully adding the task
     * @throws DukeException if user input is invalid
     */
    public static String addToDoResponse(String s, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task curr = new ToDo(s.substring(5));
            tasks.addTask(curr);
            storage.saveTasks(tasks);
            return ui.getAddTaskMessage(curr, tasks.numOfTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds an "event" task to the TaskList and returns a response.
     *
     * @param s name of the task
     * @param tasks the Tasklist where the task will be added
     * @param ui the Ui
     * @return the corresponding response upon successfully adding the task
     * @throws DukeException if user input is invalid
     */
    public static String addEventResponse(String s, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int at = s.lastIndexOf(" /at ");
            Task curr = new Event(
                    s.substring(6, at),
                    LocalDateTime.parse(s.substring(at + 5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            );
            tasks.addTask(curr);
            storage.saveTasks(tasks);
            return ui.getAddTaskMessage(curr, tasks.numOfTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description and time of an event cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException("The time must be in this format: yyyy-MM-dd HH:mm");
        }
    }

    /**
     * Adds a "deadline" task to the TaskList and returns a response.
     *
     * @param s name of the task
     * @param tasks the Tasklist where the task will be added
     * @param ui the Ui
     * @return the corresponding response upon successfully adding the task
     * @throws DukeException if user input is invalid
     */
    public static String addDeadlineResponse(String s, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int by = s.lastIndexOf(" /by ");
            Task curr = new Deadline(
                    s.substring(9, by),
                    LocalDateTime.parse(s.substring(by + 5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            );
            tasks.addTask(curr);
            storage.saveTasks(tasks);
            return ui.getAddTaskMessage(curr, tasks.numOfTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description and time of a deadline cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException("The time must be in this format: yyyy-MM-dd HH:mm");
        }
    }

    @Override
    public String getResponse (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (userInput.startsWith("todo")) {
            return addToDoResponse(userInput, tasks, ui, storage);
        } else if (userInput.startsWith("event")) {
            return addEventResponse(userInput, tasks, ui, storage);
        } else if (userInput.startsWith("deadline")) {
            return addDeadlineResponse(userInput, tasks, ui, storage);
        } else { // any other input from user
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
