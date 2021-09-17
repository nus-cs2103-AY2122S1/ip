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

    private DateTimeFormatter formatFromInput = DateTimeFormatter.ofPattern("d/MM/yyyy");

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
        if (parsedUserInput[0].equals("todo") || parsedUserInput[0].equals("t")) {
            addTodo(tasks, ui, storage, parsedUserInput);
        } else if (parsedUserInput[0].equals("deadline") || parsedUserInput[0].equals("d")) { // Add deadline
            addDeadline(tasks, ui, storage, parsedUserInput);
        } else if (parsedUserInput[0].equals("event") || parsedUserInput[0].equals("e")) { // Add event
            addEvent(tasks, ui, storage, parsedUserInput);
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
                localDate = LocalDate.parse(date, formatFromInput);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please write the date in this format: dd/MM/yyyy");
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
                localDate = LocalDate.parse(date, formatFromInput);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please write the date in this format: dd/MM/yyyy");
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
        ui.reply("Got it. I've added this duke.task: \n" + newTask.toString()
                + "     \nNow you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
