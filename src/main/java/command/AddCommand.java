package command;

import exceptions.DukeException;
import storage.Storage;
import task.*;
import ui.Ui;

import java.util.Map;

import static parser.Parser.*;

public class AddCommand extends Command {
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Usage Messages.
     */
    public static final String USAGE_TODO = String.format("Usage: %s <description>", COMMAND_TODO);
    public static final String USAGE_DEADLINE = String.format("Usage: %s <description>%s<date> <time (optional)>", COMMAND_DEADLINE, DeadlineTask.DELIMITER);
    public static final String USAGE_EVENT = String.format("Usage: %s <description>%s<date> <time (optional)>", COMMAND_EVENT, EventTask.DELIMITER);

    private final String command;
    private final String delimiter;
    private final TaskType taskType;

    private final String MESSAGE_SUCCESS = "Added task:\n" +
            "%s\n" +
            "Now you have %s task(s) in the list.";

    private final Map<TaskType, String> MESSAGE_USAGE_MAP = Map.of(
            TaskType.TODO, USAGE_TODO,
            TaskType.DEADLINE, USAGE_DEADLINE,
            TaskType.EVENT, USAGE_EVENT
    );

    private final String MESSAGE_USAGE;

    private final String ERROR_INVALID_FORMAT = "Invalid Format.";


    public AddCommand(String command, String delimiter, TaskType taskType) {
        this.command = command;
        this.delimiter = delimiter;
        this.taskType = taskType;
        this.MESSAGE_USAGE = MESSAGE_USAGE_MAP.get(taskType);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] commands = command.split(delimiter, 2);
        if (commands.length < 2) {
            throw new DukeException(MESSAGE_USAGE);
        }

        // Creates task
        Task task;

        try {
            switch (taskType) {
            case TODO:
                task = new TodoTask(commands[1]);
                break;
            case DEADLINE:
                task = DeadlineTask.getTaskFromCommandString(commands[1]);
                break;
            case EVENT:
                task = EventTask.getTaskFromCommandString(commands[1]);
                break;
            default:
                throw new DukeException(DukeException.ERROR_UNEXPECTED);
            }
        } catch (DukeException e) {
            String errorMessage = ERROR_INVALID_FORMAT + "\n" + MESSAGE_USAGE;
            throw new DukeException(errorMessage);
        }

        // Update the task list
        tasks.addTask(task);

        // Tries to write to storage first
        // If failed, remove task from list, and throw error.
        try {
            storage.writeTasksToFile(tasks);
        } catch (DukeException e) {
            tasks.removeTask(task);
            String errorMessage = e.getMessage() + "\n" + MESSAGE_USAGE;
            throw new DukeException(errorMessage);
        }

        ui.print(String.format(MESSAGE_SUCCESS, task.toString(), tasks.length()));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
