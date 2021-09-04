package duke.command;

import duke.constant.TaskType;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * The AddCommand class encapsulates the data and instructions
 * needed to add a task to the user's task list on Duke.
 */
public class AddCommand extends Command {
    private static final String EMPTY_DESCRIPTION_ERROR_MESSAGE = "The description of a task cannot be empty.\n"
            + "Please input your task in the following manner:\n"
            + "todo|deadline|event <task_description>";

    private static final String INVALID_CHARACTER_ERROR_MESSAGE =
            "The description of a task cannot contain this character: |";

    private static final String INVALID_DEADLINE_ERROR_MESSAGE = "Invalid format for a deadline task.\n"
            + "Please input your deadline task in the following manner:\n"
            + "deadline <task_description> /by <task_deadline>";

    private static final String INVALID_EVENT_ERROR_MESSAGE = "Invalid format for an event.\n"
            + "Please input your event in the following manner:\n"
            + "event <event_description> /at <event_date>";

    /** The type of task to be added. */
    private TaskType taskType;

    /** The user's input. */
    private String userInput;

    /**
     * Constructs an add command with information on the task to be added.
     *
     * @param taskType The type of task to be added.
     * @param userInput The user's input.
     */
    public AddCommand(TaskType taskType, String userInput) {
        this.taskType = taskType;
        this.userInput = userInput;
    }

    /**
     * Executes the instructions for adding a task to the user's task list on Duke.
     *
     * @param taskList Task list of the user loaded on Duke.
     * @param ui The object representing Duke's UI.
     * @param storage The object representing Duke's data and storage.
     * @return A string to be displayed to the user on the user interface.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] commandAndArgument = this.userInput.split(" ", 2);
        if (commandAndArgument.length < 2 || commandAndArgument[1].isBlank()) {
            throw new DukeException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
        }
        if (commandAndArgument[1].contains("|")) {
            throw new DukeException(INVALID_CHARACTER_ERROR_MESSAGE);
        }
        Task newTask = createTask(commandAndArgument[1]);
        taskList.add(newTask);
        storage.appendToSave(newTask);
        return ui.formatAddedTask(newTask, taskList.getNumberOfTasks());
    }

    private Task createTask(String taskDescription) {
        Task newTask = null;
        switch (this.taskType) {
        case DEADLINE:
            if (taskDescription.contains(" /by ")) {
                String[] deadlineDescriptionAndDate = taskDescription.split(" /by ", 2);
                newTask = new Deadline(deadlineDescriptionAndDate[0], deadlineDescriptionAndDate[1]);
                break;
            } else {
                throw new DukeException(INVALID_DEADLINE_ERROR_MESSAGE);
            }
        case EVENT:
            if (taskDescription.contains(" /at ")) {
                String[] eventDescriptionAndDate = taskDescription.split(" /at ", 2);
                newTask = new Event(eventDescriptionAndDate[0], eventDescriptionAndDate[1]);
                break;
            } else {
                throw new DukeException(INVALID_EVENT_ERROR_MESSAGE);
            }
        case TODO:
            newTask = new ToDo(taskDescription);
            break;
        default:
            assert false : this.taskType;
        }
        assert newTask != null : "No new task created";
        return newTask;
    }

    /**
     * Checks whether another object is equal with this add command.
     *
     * @param other The object being compared to.
     * @return true if both are add commands and share the same task type, description and date, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof AddCommand) {
            AddCommand otherCommand = (AddCommand) other;
            return this.taskType == otherCommand.taskType
                    && this.userInput.equals(otherCommand.userInput);
        } else {
            return false;
        }
    }
}
