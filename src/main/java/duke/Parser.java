package duke;

import duke.tasks.InvalidTaskException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.stream.Collectors;

/**
 * Handles matching input to behaviour and execution action.
 * Using function `takeInput` with the input string will run the function
 * and return true or false (whether or not to continue monitoring input)
 */
public class Parser {

    final private TaskList tasks;

    protected Parser(TaskList taskList) {
        this.tasks = taskList;
    }

    enum Actions {
        DELETE,
        MARK_COMPLETE,
        //  EDIT
        //  MARK_INCOMPLETE,
    }

    /**
     * Takes in and handles input for Duke (the logic).
     *
     * @param input User input passed in to Duke.
     * @return String result to be output
     */
    public String takeInput(String input) {
        if (matches("bye", input)) {
            return "Goodbye!";
        } else if (matches("", input)) {
            return "";
        } else if (matches("list", input)) {
            return listTasks();
        } else if (startsWithOrEquals("done ", input)) {
            return doTaskAction(getArgs(input, "done "), Actions.MARK_COMPLETE);
        } else if (startsWithOrEquals("delete ", input)) {
            return doTaskAction(getArgs(input, "delete "), Actions.DELETE);
        } else if (startsWithOrEquals("todo ", input)) {
            return addTask(getArgs(input, "todo "), Task.Type.TODO);
        } else if (startsWithOrEquals("deadline ", input)) {
            return addTask(getArgs(input, "deadline "), Task.Type.DEADLINE);
        } else if (startsWithOrEquals("event ", input)) {
            return addTask(getArgs(input, "event "), Task.Type.EVENT);
        } else if (matches("reset", input)) {
            tasks.clear();
            return "Cleared";
        } else {
            return "I did not understand, sorry!";
        }
    }

    private boolean matches(String phrase, String input) {
        return input.trim().equalsIgnoreCase(phrase);
    }

    private boolean startsWithOrEquals(String phrase, String input) {
        return input.trim().startsWith(phrase) || input.trim().equalsIgnoreCase(phrase.trim());
    }

    private boolean contains(String phrase, String input) {
        return input.trim().contains(phrase);
    }

    private String getArgs(String input, String command) {
        return input.substring(input.toLowerCase().indexOf(command) + command.length()).trim();
    }

    private String listTasks() {
        int taskCount = 1;
        StringBuilder result = new StringBuilder();
        for (Task task : this.tasks.stream().collect(Collectors.toList())) {
            result.append(String.format("%2d. %s\n", taskCount++, task));
        }
        return result.toString();
    }

    private String addTask(String taskName, Task.Type type) {
        try {
            Task task = Task.createTask(taskName.trim(), type);
            tasks.add(task);
            return "added: " + task;
        } catch (InvalidTaskException err) {
            return err.getMessage();
        }
    }

    private String doTaskAction(String taskNumStr, Actions action) {
        try {
            int taskNum = Integer.parseInt(taskNumStr);
            Task task = tasks.get(taskNum - 1);
            String output = "";
            switch (action) {
            case DELETE:
                tasks.remove(taskNum - 1);
                output += "Noted. I have deleted the following:\n    " + task;
                output += String.format(
                        "%s\nYou now have %d tasks in the list", output, tasks.size());
                break;
            case MARK_COMPLETE:
                task.markComplete(true);
                output += "Great! I've marked this task as done:\n    " + task;
                break;
            default:
                throw new IllegalArgumentException("Invalid action");
            }
            return output;
        } catch (NumberFormatException err) {
            return "Which task are you interacting with?\n"
                    + "USAGE:\n{action} {task number}\n"
                    + "Example: > done 4\n"
                    + "         > delete 2\n"
                    + "Try the `list` command to see the list of tasks";
        } catch (IndexOutOfBoundsException err) {
            return "There is no task at that index.";
        }
    }
}
