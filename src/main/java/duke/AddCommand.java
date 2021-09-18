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
     * @param userInput user input
     * @param tasks the Tasklist where the task will be added
     * @param ui the Ui
     * @return the corresponding response upon successfully adding the task
     * @throws DukeException if user input is invalid
     */
    private static String addToDoResponse(String userInput, TaskList tasks, Ui ui,
            Storage storage) throws DukeException {

        String todoFormat =
                "Correct format for adding a todo:\ntodo TODO_DESCRIPTION";

        String description = userInput.substring(4).trim();

        if (description.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty\n\n" + todoFormat);
        }

        Task curr = new ToDo(description);
        tasks.addTask(curr);
        storage.saveTasks(tasks);

        return ui.getAddTaskMessage(curr, tasks.numOfTasks());
    }

    /**
     * Adds an "event" task to the TaskList and returns a response.
     *
     * @param userInput user input
     * @param tasks the Tasklist where the task will be added
     * @param ui the Ui
     * @return the corresponding response upon successfully adding the task
     * @throws DukeException if user input is invalid
     */
    private static String addEventResponse(String userInput, TaskList tasks, Ui ui,
            Storage storage) throws DukeException {

        String eventFormat =
                "Correct format for adding an event:\nevent EVENT_DESCRIPTION /at yyyy-MM-dd HH:mm";

        if (!userInput.contains("/at")) {
            throw new DukeException("Please specify the time with /at\n\n" + eventFormat);
        }

        String[] eventData = (userInput + " ").split("/at");
        String description = eventData[0].substring(5).trim();
        String dateTime = eventData[1].trim();

        if (description.length() == 0) {
            throw new DukeException("The description of an event cannot be empty\n\n" + eventFormat);
        }

        try {
            Task curr = new Event(
                    description,
                    LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            );
            tasks.addTask(curr);
            storage.saveTasks(tasks);

            return ui.getAddTaskMessage(curr, tasks.numOfTasks());
        } catch (DateTimeParseException e) {
            throw new DukeException("The time must be in this format: yyyy-MM-dd HH:mm\n\n" + eventFormat);
        }
    }

    /**
     * Adds a "deadline" task to the TaskList and returns a response.
     *
     * @param userInput user input
     * @param tasks the Tasklist where the task will be added
     * @param ui the Ui
     * @return the corresponding response upon successfully adding the task
     * @throws DukeException if user input is invalid
     */
    private static String addDeadlineResponse(String userInput, TaskList tasks, Ui ui,
            Storage storage) throws DukeException {

        String deadlineFormat =
                "Correct format for adding a deadline:\ndeadline DEADLINE_DESCRIPTION /by yyyy-MM-dd HH:mm";

        if (!userInput.contains("/by")) {
            throw new DukeException("Please specify the time with /by\n\n" + deadlineFormat);
        }

        String[] deadlineData = (userInput + " ").split("/by");
        String description = deadlineData[0].substring(8).trim();
        String dateTime = deadlineData[1].trim();

        if (description.length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty\n\n" + deadlineFormat);
        }

        try {
            Task curr = new Deadline(
                    description,
                    LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            );
            tasks.addTask(curr);
            storage.saveTasks(tasks);

            return ui.getAddTaskMessage(curr, tasks.numOfTasks());
        } catch (DateTimeParseException e) {
            throw new DukeException("The time must be in this format: yyyy-MM-dd HH:mm\n\n" + deadlineFormat);
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
