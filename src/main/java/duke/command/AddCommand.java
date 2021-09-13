package duke.command;

import static duke.parser.Parser.COMMAND_TODO;
import static duke.parser.Parser.COMMAND_DEADLINE;
import static duke.parser.Parser.COMMAND_EVENT;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoTask;

import java.util.Map;

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

    private final String COMMAND;
    private final String DELIMITER;
    private final TaskType TASKTYPE;

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

    /**
     * Constructor for AddCommand.
     *
     * @param command String duke.command input by user.
     * @param delimiter String delimiter between the commands in the duke.command string input.
     * @param taskType TaskType input.
     */
    public AddCommand(String command, String delimiter, TaskType taskType) {
        this.COMMAND = command;
        this.DELIMITER = delimiter;
        this.TASKTYPE = taskType;
        this.MESSAGE_USAGE = MESSAGE_USAGE_MAP.get(taskType);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String[] commands = COMMAND.split(DELIMITER, 2);
        if (commands.length < 2) {
            throw new DukeException(MESSAGE_USAGE);
        }

        // Creates duke.task
        Task task;

        try {
            switch (TASKTYPE) {
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

        // Update the duke.task list
        tasks.addTask(task);

        // Tries to write to duke.storage first
        // If failed, remove duke.task from list, and throw error.
        try {
            storage.writeTasksToFile(tasks);
        } catch (DukeException e) {
            tasks.removeTask(task);
            String errorMessage = e.getMessage() + "\n" + MESSAGE_USAGE;
            throw new DukeException(errorMessage);
        }

        return String.format(MESSAGE_SUCCESS, task.toString(), tasks.length());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
