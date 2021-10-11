package duke.commands;

import static java.util.Map.entry;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.InvalidTaskException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.UserInput;

/**
 * A command that adds a specified task to the list.
 *
 * @author cai
 */
class AddTaskCommand extends Command {
    /** Maps a keyword to its corresponding task type */
    private static final Map<String, TaskType> KEYWORD_TO_TASKTYPE = Map.ofEntries(
            entry("todo", TaskType.TODO),
            entry("deadline", TaskType.DEADLINE),
            entry("event", TaskType.EVENT),
            entry("t", TaskType.TODO),
            entry("d", TaskType.DEADLINE),
            entry("e", TaskType.EVENT)
    );
    private static final String ADD_TASK_SUCCESS_MESSAGE = "I've added this task:\n\t%s\n" + TASKS_COUNT_MESSAGE;
    private static final String DEADLINE_BY_REGEX = "(?i)\\s+/by\\s+";
    private static final String EVENT_AT_REGEX = "(?i)\\s+/at\\s+";

    /** The possible types of task to add */
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    @Override
    public void run(Duke duke, UserInput input) throws InvalidTaskException {
        assert input != null;
        Task newTask = createTask(input);
        TaskList taskList = duke.getTaskList();
        taskList.add(newTask);
        duke.say(String.format(ADD_TASK_SUCCESS_MESSAGE, newTask, taskList.size()));
    }

    /**
     * Constructs a Task corresponding to the provided user input.
     *
     * @param input The input containing the type and details of the task.
     * @return The corresponding task with the specified type and details.
     * @throws InvalidTaskException If invalid task details were provided.
     */
    private static Task createTask(UserInput input) throws InvalidTaskException {
        TaskType taskType = KEYWORD_TO_TASKTYPE.get(input.getKeyword().toLowerCase());
        assert taskType != null;

        try {
            switch (taskType) {
            case TODO:
                return new Todo(input.getArgs());
            case DEADLINE: {
                String[] splitInput = input.getArgs().split(DEADLINE_BY_REGEX, 2);
                String dateString = splitInput.length > 1 ? splitInput[1] : "";
                return new Deadline(splitInput[0], LocalDate.parse(dateString));
            }
            case EVENT: {
                String[] splitInput = input.getArgs().split(EVENT_AT_REGEX, 2);
                String dateString = splitInput.length > 1 ? splitInput[1] : "";
                return new Event(splitInput[0], LocalDate.parse(dateString));
            }
            default:
                throw new InvalidTaskException("Invalid task type");
            }
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException("Invalid date provided", e);
        }
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORD_TO_TASKTYPE.keySet();
    }
}
