package Duke.Commands;

import Duke.Duke;
import Duke.Task.*;
import Duke.Ui.Ui;
import Duke.Ui.UserInput;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class AddTaskCommand extends Command {
    // Use the names of the enum values as keywords
    private static final Set<String> KEYWORDS = new HashSet<>(
            Arrays.stream(TaskType.values())
                    .map(Enum::name)
                    .collect(Collectors.toList())
    );
    private static final String ADD_TASK_SUCCESS_MESSAGE = "I've added this task:\n\t%s\n" + TASKS_COUNT_MESSAGE;
    private static final String DEADLINE_BY_REGEX = "(?i)\\s+/by\\s+";
    private static final String EVENT_AT_REGEX = "(?i)\\s+/at\\s+";

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
