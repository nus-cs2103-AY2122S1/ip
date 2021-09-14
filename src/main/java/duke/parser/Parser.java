package duke.parser;

import java.time.format.DateTimeParseException;
import java.util.List;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TimedTask;
import duke.task.Todo;

/**
 * Represents a parser that interprets the user's inputs into commands.
 */
public class Parser {
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!\n";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    private static final String TODO_MESSAGE = "Got it. I've added this task:\n";
    private static final String SNOOZE_MESSAGE = "Okay, I've snoozed this task:\n";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:\n";
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:\n";

    private static final String INVALID_INPUT = "OOPS!!! I'm sorry, but I don't know what that means :(";
    private static final String EMPTY_LIST = "Yay, you have no items in your list!";
    private static final String EMPTY_DESCRIPTION = "OOPS!!! The description of a Task cannot be empty.";
    private static final String EMPTY_DATE = "Please include a date for this command.";
    private static final String WORD_NOT_FOUND = "OOPS!!! There's no such word in your list.";
    private static final String MISSING_FIND_PARAMETER = "Please include the word to be searched.";
    private static final String WRONG_DATE_FORMAT = "Please input the date in the following format: dd-mm-yyyy HH:mm.";

    /**
     * Turns the user's input into a command.
     *
     * @param userInput the user's input given as a string.
     * @param taskList  the list containing the tasks.
     * @param duke      the Duke object running.
     * @return a string to be shown in the GUI.
     */
    public static String parse(String userInput, TaskList taskList, Duke duke) {
        String[] tokens = userInput.split("\\s+", 2);
        String command = tokens[0];
        String param = tokens.length == 1 ? null : tokens[1].strip();

        String[] taskItems;
        String taskName;
        Task task;
        int intParam;
        StringBuilder output = new StringBuilder();

        switch (command) {
        case "todo":
            if (param == null) {
                output.append(EMPTY_DESCRIPTION);
                break;
            }

            task = new Todo(param);
            taskList.add(task);

            output.append(TODO_MESSAGE);
            output.append(taskList.get(taskList.getSize() - 1)).append(System.lineSeparator());
            output.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
            break;
        case "list":
            List<String> enumerate = taskList.enumerate();
            if (enumerate.isEmpty()) {
                output.append(EMPTY_LIST);
                break;
            }

            output.append(LIST_MESSAGE);
            for (String currentTaskName : enumerate) {
                output.append(currentTaskName).append(System.lineSeparator());
            }
            output.append("There are currently ").append(taskList.getSize()).append(" tasks in your list.");
            break;
        case "find":
            enumerate = taskList.filter(param).enumerate();
            if (param == null) {
                output.append(MISSING_FIND_PARAMETER);
                break;
            } else if (enumerate.isEmpty()) {
                output.append(WORD_NOT_FOUND);
                break;
            }

            output.append(FIND_MESSAGE);
            for (String currentTaskName : enumerate) {
                output.append(currentTaskName).append(System.lineSeparator());
            }
            break;
        case "deadline":
            assert param != null;
            taskItems = param.split(" /by ", 2);
            taskName = taskItems[0].strip();

            if (taskItems.length == 1) {
                output.append(EMPTY_DATE);
                break;
            }

            try {
                taskList.add(new Deadline(taskName, taskItems[1].strip()));
            } catch (DateTimeParseException DateException) {
                output.append(WRONG_DATE_FORMAT);
                break;
            }

            output.append(TODO_MESSAGE);
            output.append(taskList.get(taskList.getSize() - 1)).append(System.lineSeparator());
            output.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
            break;
        case "event":
            assert param != null;
            taskItems = param.split(" /at ", 2);
            taskName = taskItems[0].strip();

            if (taskItems.length == 1) {
                output.append(EMPTY_DATE);
                break;
            }

            try {
                taskList.add(new Event(taskName, taskItems[1].strip()));
            } catch (DateTimeParseException DateException) {
                output.append(WRONG_DATE_FORMAT);
                break;
            }

            output.append(TODO_MESSAGE);
            output.append(taskList.get(taskList.getSize() - 1)).append(System.lineSeparator());
            output.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
            break;
        case "snooze":
            assert param != null;
            taskItems = param.split(" /to ", 2);
            intParam = Integer.parseInt(taskItems[0].strip()) - 1;

            if (taskItems.length == 1) {
                output.append(EMPTY_DATE);
                break;
            } else if (!(taskList.get(intParam) instanceof TimedTask)) {
                output.append("Oops!!! You cannot snooze this Task.");
                break;
            }

            try {
                TimedTask taskToBeChanged = (TimedTask) taskList.get(intParam);
                taskToBeChanged.changeDate(taskItems[1].strip());
            } catch (DateTimeParseException DateException) {
                output.append(WRONG_DATE_FORMAT);
                break;
            }

            output.append(SNOOZE_MESSAGE);
            output.append(taskList.get(intParam)).append(System.lineSeparator());
            break;
        case "done":
            assert param != null;
            intParam = Integer.parseInt(param) - 1;
            taskList.get(intParam).markAsDone();

            output.append(DONE_MESSAGE);
            output.append(taskList.get(intParam));
            break;
        case "delete":
            assert param != null;
            intParam = Integer.parseInt(param) - 1;

            output.append(DELETE_MESSAGE);
            output.append(taskList.get(intParam)).append(System.lineSeparator());
            taskList.remove(intParam);
            output.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
            break;
        case "bye":
            output.append(BYE_MESSAGE);
            break;
        default:
            output.append(INVALID_INPUT);
        }
        duke.save(taskList);
        return output.toString();
    }
}
