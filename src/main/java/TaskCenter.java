import java.util.ArrayList;
import java.util.List;

public class TaskCenter {
    /** The list of tasks. */
    private List<Task> tasks = new ArrayList<>();

    public TaskOperation createTaskOperation(String input)
            throws DukeException, NumberFormatException, IndexOutOfBoundsException {
        if (input.equals("list")) {
            return new ListTasks(tasks);
        }

        String[] inputs = input.split(" ", 2);
        String keyword = inputs[0];

        switch (keyword) {
        // Commands for creating tasks
        case "todo":
        case "deadline":
        case "event": {
            checkMissingArguments(inputs, String.format("The description of a %s cannot be empty.\n", keyword));
            Task newTask = Task.createTask(inputs);
            return new AddTask(tasks, newTask);
        }
        // Commands for existing tasks
        case "done": {
            checkMissingArguments(inputs, "Please specify a task number to mark as done.\n");
            int index = Integer.parseInt(inputs[1]) - 1;
            Task task = tasks.get(index);
            return new MarkTaskDone(tasks, task);
        }
        case "delete": {
            checkMissingArguments(inputs, "Please specify a task number for deletion.\n");
            int index = Integer.parseInt(inputs[1]) - 1;
            return new DeleteTask(tasks, index);
        }
        default:
            throw new InvalidArgumentException();
        }
    }

    public static void checkMissingArguments(String[] sections, String errorMessage) throws MissingArgumentException {
        if (sections.length != 2 || sections[1].isEmpty()) {
            throw new MissingArgumentException(errorMessage);
        }
    }
}
