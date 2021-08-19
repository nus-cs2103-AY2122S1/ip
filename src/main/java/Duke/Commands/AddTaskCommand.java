package Duke.Commands;

import Duke.Duke;
import Duke.Task.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class AddTaskCommand extends Command {
    private static final Set<String> KEYWORDS = new HashSet<>(List.of("todo", "deadline", "event"));
    private static final String ADD_TASK_SUCCESS_MESSAGE = "I've added this task:\n\t%s\n"
            + "Now you have %d tasks in the list.";
    private static final String DEADLINE_BY_REGEX = "\\s+/by\\s+";
    private static final String EVENT_AT_REGEX = "\\s+/at\\s+";

    @Override
    public void run(Duke duke, Duke.UserInput input) {
        Task newTask = createTask(input);
        TaskList taskList = duke.getTaskList();
        taskList.add(newTask);
        duke.say(String.format(ADD_TASK_SUCCESS_MESSAGE, newTask, taskList.size()));
    }

    private static Task createTask(Duke.UserInput input) {
        // TODO: use enums for this
        switch (input.getKeyword()) {
            case "todo":
                return new Todo(input.getArgs());
            case "deadline": {
                // TODO: throw IllegalArgumentException if splitInput.length() == 1
                String[] splitInput = input.getArgs().split(DEADLINE_BY_REGEX, 2);
                return new Deadline(splitInput[0], splitInput[1]);
            }
            case "event": {
                String[] splitInput = input.getArgs().split(EVENT_AT_REGEX, 2);
                return new Event(splitInput[0], splitInput[1]);
            }
        }

        // TODO: invalid task type
        return null;
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORDS;
    }
}
