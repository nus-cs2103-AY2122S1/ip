package duke.command;

import java.time.LocalDateTime;

import duke.constant.MessageType;
import duke.exception.DukeExtractCommandException;
import duke.listener.Message;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.EventDateTime;
import duke.task.Operation;
import duke.task.TaskList;
import duke.task.Todo;
import duke.util.CommandUtils;

/**
 * This is the AddCommand class that add task.
 */
public class AddCommand extends Command {
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String REGEX_AT = " /at ";
    private static final String REGEX_BY = " /by ";
    private static final String REGEX_SPACE = " ";

    private final String command;
    private final Operation operation;

    /**
     * Constructs a AddCommand object.
     *
     * @param command Command from user input.
     * @param operation Operation.
     * @param message Message interface.
     */
    public AddCommand(String command, Operation operation, Message message) {
        super(message);
        this.operation = operation;
        this.command = command;
    }

    private String getTaskDescription(String command) {
        String description = "";
        try {
            description = CommandUtils.extractTaskDescription(command);
        } catch (DukeExtractCommandException e) {
            getMessage().show(MessageType.ERROR, e.getMessage());
        }
        return description;
    }

    private void addTodoTask(TaskList taskList, String description) {
        Todo todo = new Todo(description);
        taskList.addTask(todo);
        getMessage().show(MessageType.NORMAL,
                TASK_ADDED_MESSAGE,
                "  " + todo.toString(),
                "Now you have " + taskList.getSize()
                        + " " + (taskList.getSize() <= 1 ? "task" : "tasks")
                        + " in the list.");
    }

    private void addDeadlineTask(TaskList taskList, String description) {
        try {
            String[] taskDetails = CommandUtils.extractTaskDetails(description, REGEX_BY);
            assert taskDetails.length == 2
                    : "☹ OOPS!!! TaskDetails cannot be split to 2 parts.";
            String taskName = taskDetails[0];
            LocalDateTime byDateTime = CommandUtils.extractDeadlineDateTime(taskDetails[1]);
            Deadline deadline = new Deadline(taskName, byDateTime);
            taskList.addTask(deadline);
            getMessage().show(MessageType.NORMAL,
                    TASK_ADDED_MESSAGE,
                    "  " + deadline.toString(),
                    "Now you have " + taskList.getSize()
                            + " " + (taskList.getSize() <= 1 ? "task" : "tasks")
                            + " in the list.");
        } catch (DukeExtractCommandException e) {
            getMessage().show(MessageType.ERROR, e.getMessage());
        }
    }

    private void addEventTask(TaskList taskList, String description) {
        try {
            String[] taskDetails = CommandUtils.extractTaskDetails(description, REGEX_AT);
            assert taskDetails.length == 2
                    : "☹ OOPS!!! TaskDetails cannot be split to 2 parts.";
            String taskName = taskDetails[0];
            EventDateTime eventDateTime = CommandUtils
                .extractEventDatetime(taskDetails[1], REGEX_SPACE);
            Event event = new Event(taskName, eventDateTime);
            taskList.addTask(event);
            getMessage().show(MessageType.NORMAL,
                    TASK_ADDED_MESSAGE,
                    "  " + event.toString(),
                    "Now you have " + taskList.getSize() + " "
                            + (taskList.getSize() <= 1 ? "task" : "tasks")
                            + " in the list.");
        } catch (DukeExtractCommandException e) {
            getMessage().show(MessageType.ERROR, e.getMessage());
        }
    }

    /**
     * Adds task.
     *
     * @param taskList TaskList.
     */
    @Override
    public void execute(TaskList taskList) {
        String description = getTaskDescription(command);
        if (description.equals("")) {
            return;
        }
        switch (operation) {
        case TODO:
            addTodoTask(taskList, description);
            break;
        case DEADLINE:
            addDeadlineTask(taskList, description);
            break;
        case EVENT:
            addEventTask(taskList, description);
            break;
        default:
            break;
        }
    }
}
