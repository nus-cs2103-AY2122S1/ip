package duke.command;

import duke.data.Storage;
import duke.exceptions.InvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * AddTaskCommand object allows a Task to be added to TaskList.
 */
public class AddTaskCommand extends Command {
    /** Attributes for a AddTaskCommand object */
    private CommandType taskType;
    private String taskName;
    private String dateAndTime;

    /**
     * Initialises a AddTaskCommand object.
     *
     * @param taskType the type of task to be added
     * @param taskName the task name of the added task
     */
    public AddTaskCommand(CommandType taskType, String taskName) {
        this.taskType = taskType;
        this.taskName = taskName;
    }

    /**
     * Initialises a AddTaskCommand object with a dateAndTime attribute.
     *
     * @param taskType the type of task to be added
     * @param taskName the task name of the added task
     * @param dateAndTime the string representing the date and time for the added task
     */
    public AddTaskCommand(CommandType taskType, String taskName, String dateAndTime) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.dateAndTime = dateAndTime;
    }

    /**
     * Returns the task to be added according to the user command and adds the task to the task list.
     * Throws an InvalidInputException when the tasked added is not of type Todo, Deadline or Event.
     *
     * @param tasks the task list whereby tasks are added to
     * @return the added task
     * @throws InvalidInputException when the task is not a Todo, Deadline or Event
     */
    private Task addTask(TaskList tasks) throws InvalidInputException {
        switch (taskType) {
        case TODO:
            Todo todo = new Todo(taskName);
            tasks.addTask(todo);
            return todo;
        case DEADLINE:
            Deadline deadline = new Deadline(taskName, dateAndTime);
            tasks.addTask(deadline);
            return deadline;
        case EVENT:
            Event event = new Event(taskName, dateAndTime);
            tasks.addTask(event);
            return event;
        default:
            throw new InvalidInputException(Message.MESSAGE_ERROR_OCCURRED);
        }
    }

    /**
     * Executes the AddTaskCommand object.
     *
     * @param taskList the current task list
     * @param ui the ui object used
     * @param storage the current storage
     * @return a message string of the task added
     * @throws InvalidInputException when task fails to add
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        Task addedTask = addTask(taskList);
        storage.update(taskList);
        return ui.showAddTaskMessage(addedTask, taskList);
    }
}
