package Duke;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.function.BiFunction;
import java.time.format.DateTimeParseException;

public class Parser {
    private final String MSG_LISTS = "Here are the tasks in your list:";
    private final String MSG_TASK_COMPLETE = "Nice! I've marked this task as done:\n%s";
    private final String MSG_TASK_ADDED = "Got it. I've added this task:";
    private final String MSG_TASK_DELETED = "Noted. I've removed this task:";
    private final String MSG_TASK_COUNT = "Now you have %d tasks in the list.";
    private final String ERR_OUT_OF_BOUNDS = "Please enter a number between 1 and %d!";
    private final String ERR_TASK_COMPLETE = "Task %s is already complete!";
    private final String ERR_NOT_FOUND = "Sorry, I do not recognize that command.";
    private final String ERR_NO_TASKS = "No tasks available!";
    private final String ERR_TODO_FORMAT = "Error in command usage. Usage: todo <name>";
    private final String ERR_DEADLINE_FORMAT = "Error in command usage. Usage: deadline <name> /by dd/MM/yyyy HHmm";
    private final String ERR_EVENT_FORMAT = "Error in command usage. Usage: event <name> /at dd/MM/yyyy HHmm";
    private final String ERR_DONE_FORMAT = "Please provide a valid number! Usage: done <num>";
    private final String ERR_DELETE_FORMAT = "Please provide a valid number! Usage: delete <num>";
    private final String ERR_MAX_TASKS = "Sorry! You have reached maximum Task capacity.";
    private final int MAX_TASKS = 100;
    private final Map<String, BiFunction<String, TaskList, String>> commandsMap = Map.of(
        "list", (args, tasks) -> listTasks(tasks),
        "done", (args, tasks) -> completeTask(args, tasks),
        "todo", (args, tasks) -> addTodo(args, tasks),
        "deadline", (args, tasks) -> addDeadline(args, tasks),
        "event", (args, tasks) -> addEvent(args, tasks),
        "delete", (args, tasks) -> deleteTask(args, tasks)
    );

    /**
     * Executes a command represented by input string and updates provided tasklist.
     *
     * @param input command to execute
     * @param tasks TaskList to update
     * @return message to present to the user
     */
    public String execute(String input, TaskList tasks) {
        String[] tmp =  input.split(" ", 2);
        String command = tmp[0];
        String args = tmp.length > 1 ? tmp[1] : "";
        return commandsMap.getOrDefault(command, (str, tasklist) -> notFound())
            .apply(args, tasks);
    }


    private String latestTask(TaskList tasks) {
        return MSG_TASK_ADDED + "\n"
            + "   " + tasks.get(tasks.size() - 1).toString() + "\n"
            + String.format(MSG_TASK_COUNT, tasks.size());
    }

    private String addTodo(String args, TaskList tasks) {
        if (tasks.size() > MAX_TASKS) {
            return ERR_MAX_TASKS;
        }
        if (args.equals("")) {
            return ERR_TODO_FORMAT;
        }
        tasks.add(new Todo(args));
        return latestTask(tasks);
    }

    private String addDeadline(String args, TaskList tasks) {
        if (!args.contains(" /by ")) {
            return ERR_DEADLINE_FORMAT;
        }
        try {
            String[] tmp = args.split(" /by ", 2);
            tasks.add(new Deadline(tmp[0], tmp[1]));
            return latestTask(tasks);
        } catch (DateTimeParseException e) {
            return ERR_DEADLINE_FORMAT;
        }
    }

    private String addEvent(String args, TaskList tasks) {
        if (!args.contains(" /at ")) {
            return ERR_EVENT_FORMAT;
        }
        try {
            String[] tmp = args.split(" /at ", 2);
            tasks.add(new Event(tmp[0], tmp[1]));
            return latestTask(tasks);
        } catch (DateTimeParseException e) {
            return ERR_EVENT_FORMAT;
        }
    }

    private String listTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            return ERR_NO_TASKS;
        }
        return MSG_LISTS + "\n" + String.join("\n", IntStream.range(0, tasks.size())
                .mapToObj(i -> String.format("%d.%s", i + 1, tasks.get(i)))
                .toArray(String[]::new));
    }

    private String completeTask(String args, TaskList tasks) {
        try {
            int idx = Integer.parseInt(args);
            if (tasks.size() == 0) {
                return ERR_NO_TASKS;
            }
            if (idx < 1 || idx > tasks.size()) {
                return String.format(ERR_OUT_OF_BOUNDS, tasks.size());
            }
            if (tasks.get(idx - 1).isComplete()) {
                return String.format(ERR_TASK_COMPLETE, tasks.get(idx - 1).getName());
            }
            tasks.markComplete(idx);
            return String.format(MSG_TASK_COMPLETE, String.format("   %s", tasks.get(idx - 1)));
        } catch (NumberFormatException e) {
            return ERR_DONE_FORMAT;
        }
    }

    private String deleteTask(String args, TaskList tasks) {
        try {
            int idx = Integer.parseInt(args);
            if (tasks.size() == 0) {
                return ERR_NO_TASKS;
            }
            if (idx < 1 || idx > tasks.size()) {
                return String.format(ERR_OUT_OF_BOUNDS, tasks.size());
            }
            Task deletedTask = tasks.get(idx - 1);
            tasks.remove(idx - 1);
            return MSG_TASK_DELETED + "\n"
                + "   " + deletedTask.toString() + "\n"
                + String.format(MSG_TASK_COUNT, tasks.size());
        } catch (NumberFormatException e) {
            return ERR_DELETE_FORMAT;
        }
    }

    private String notFound() {
        return ERR_NOT_FOUND;
    }
}
