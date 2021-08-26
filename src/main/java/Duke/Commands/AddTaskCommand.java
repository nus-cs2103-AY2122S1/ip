package Duke.Commands;

import Duke.Duke;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.InvalidTaskException;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Task.Todo;
import Duke.Ui.Ui;
import Duke.Ui.UserInput;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A command that adds a specified task to the list.
 *
 * @author cai
 */
class AddTaskCommand extends Command {
    /**
     * The list of keywords associated with adding a task.
     * The names of the values in the enum `TaskType` is used as keywords.
     */
    private static final Set<String> KEYWORDS = new HashSet<>(
            Arrays.stream(TaskType.values())
                    .map(Enum::name)
                    .collect(Collectors.toList())
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
        Task newTask = createTask(input);
        TaskList taskList = duke.getTaskList();
        taskList.add(newTask);
        Ui.print(String.format(ADD_TASK_SUCCESS_MESSAGE, newTask, taskList.size()));
    }

    /**
     * Constructs a Task corresponding to the provided user input.
     *
     * @param input The input containing the type and details of the task.
     * @return The corresponding task with the specified type and details.
     * @throws InvalidTaskException If invalid task details were provided.
     */
    private static Task createTask(UserInput input) throws InvalidTaskException {
        TaskType taskType = TaskType.valueOf(input.getKeyword().toUpperCase());
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
            }
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException("Invalid date provided", e);
        }

        throw new InvalidTaskException("Invalid task type");
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORDS;
    }
}
