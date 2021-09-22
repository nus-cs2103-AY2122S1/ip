package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class AddTaskCommand extends Command {

    private final DateTimeFormatter FORMAT_FROM_INPUT = DateTimeFormatter.ofPattern("d/MM/yyyy");

    private final String TODO_NAME = "todo";
    private final String DEADLINE_NAME = "deadline";
    private final String EVENT_NAME = "event";

    private final String ERROR_MESSAGE_WRONG_DATE_FORMAT = "Please write the date in this format: dd/MM/yyyy";
    private final String ERROR_WRONG_INPUT_FORMAT = "OOWOOPS!!! I'm sowwie, but I don't know what that mweans :-(";


    /**
     * Constructor for class AddTaskCommand
     *
     * @param userInput  user's input
     */
    public AddTaskCommand(String userInput) {
        super(userInput);
    }

    /**
     * Adds the task to the tasks
     * If the input is not parsable, a DukeException is thrown
     *
     * @param tasks contains the task list
     * @param ui deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException If user input is incorrect
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parsedUserInput = this.getUserInput().split(" ", 2);
        switch (parsedUserInput[0]) {
        case TODO_NAME:
        case "t":
            addTodo(tasks, ui, storage, parsedUserInput);
            break;
        case DEADLINE_NAME:
        case "d": // Add deadline
            addDeadline(tasks, ui, storage, parsedUserInput);
            break;
        case EVENT_NAME:
        case "e": // Add event
            addEvent(tasks, ui, storage, parsedUserInput);
            break;
        default:
            throw new DukeException(ERROR_WRONG_INPUT_FORMAT);
        }
    }

    /**
     * Adds the todo to the tasks
     * If the input is not parsable, a DukeException is thrown
     *
     * @param tasks contains the task list
     * @param ui deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException If user input is incorrect
     */
    private void addTodo(TaskList tasks, Ui ui, Storage storage, String[] parsedUserInput) throws DukeException {
        if (parsedUserInput.length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTask = new ToDo(parsedUserInput[1]);
        addTaskToList(newTask, ui, tasks, storage);
    }

    /**
     * Adds the Deadline to the tasks
     * If the input is not parsable, a DukeException is thrown
     *
     * @param tasks contains the task list
     * @param ui deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException If user input is incorrect
     */
    private void addDeadline(TaskList tasks, Ui ui, Storage storage, String[] parsedUserInput) throws DukeException {
        if (parsedUserInput.length == 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!parsedUserInput[1].contains("/by")) {
            throw new DukeException("Please include the keyword \"/by\" if you want to add a deadline.");
        } else {
            String[] parsedDeadlineInput = parsedUserInput[1].split("/by ", 2);
            String date = parsedDeadlineInput[1];
            LocalDate localDate;
            try {
                localDate = LocalDate.parse(date, FORMAT_FROM_INPUT);
            } catch (DateTimeParseException e) {
                throw new DukeException(ERROR_MESSAGE_WRONG_DATE_FORMAT);
            }
            Task newTask = new Deadline(parsedDeadlineInput[0], localDate);
            addTaskToList(newTask, ui, tasks, storage);
        }
    }

    /**
     * Adds the Event to the tasks
     * If the input is not parsable, a DukeException is thrown
     *
     * @param tasks contains the task list
     * @param ui deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @throws DukeException If user input is incorrect
     */
    private void addEvent(TaskList tasks, Ui ui, Storage storage, String[] parsedUserInput) throws DukeException {
        if (parsedUserInput.length == 1) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (!parsedUserInput[1].contains("/at")) {
            throw new DukeException("Please include the keyword \"/at\" if you want to add an event.");
        } else {
            String[] parsedDeadlineInput = parsedUserInput[1].split("/at ", 2);
            String date = parsedDeadlineInput[1];
            LocalDate localDate;
            try {
                localDate = LocalDate.parse(date, FORMAT_FROM_INPUT);
            } catch (DateTimeParseException e) {
                throw new DukeException(ERROR_MESSAGE_WRONG_DATE_FORMAT);
            }
            Task newTask = new Event(parsedDeadlineInput[0], localDate);
            addTaskToList(newTask, ui, tasks, storage);
        }
    }

    /**
     * Adds task to list and prints out what has been added
     *
     * @param newTask task to be added
     * @param ui deals with interactions with the user
     * @param tasks contains the task list
     * @param storage deals with loading tasks from the file and saving tasks in the file
     */
    private void addTaskToList(Task newTask, Ui ui, TaskList tasks, Storage storage) {
        tasks.getTasks().add(newTask);
        storage.updateLocalStorage(tasks.getTasks());
        ui.reply("Got it. I've added this task: \n" + newTask.toString()
                + "\nNow you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
