package duke.parser;

import java.util.List;

import duke.Duke;
import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a parser that interprets the user's inputs into commands.
 */
public class Parser {
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!\n";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    private static final String TODO_MESSAGE = "Got it. I've added this task:\n";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:\n";
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:\n";

    /**
     * Interprets the user's input and handles the logic of the command.
     *
     * @param userInput the user's input given as a string.
     * @param taskList  the list containing the tasks.
     * @param duke      the Duke object running.
     * @return a string to be shown in the GUI.
     * @throws DukeException if the inputs are not expected.
     */
    public static String parse(String userInput, TaskList taskList, Duke duke) throws DukeException {
        String[] tokens = userInput.split("\\s+", 2);
        String command = tokens[0];
        String param = tokens.length == 1 ? null : tokens[1].strip();

        String[] taskItems;
        String taskName;
        Task task;
        StringBuilder output = new StringBuilder();

        switch (command) {
        case "todo":
            if (param == null) {
                throw DukeException.emptyDescription();
            }

            task = new Todo(param);
            taskList.add(task);

            output.append(TODO_MESSAGE);
            output.append(taskList.get(taskList.getSize() - 1)).append(System.lineSeparator());
            output.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
            break;
        case "list":
            List<String> enumerate = taskList.enumerate();

            for (String currentTaskName : enumerate) {
                output.append(currentTaskName).append(System.lineSeparator());
            }
            output.append(LIST_MESSAGE);
            output.append("There are currently ").append(taskList.getSize()).append(" tasks in your list.");
            break;
        case "find":
            enumerate = taskList.filter(param).enumerate();

            output.append(FIND_MESSAGE);
            for (String currentTaskName : enumerate) {
                output.append(currentTaskName).append(System.lineSeparator());
            }
            break;
        case "deadline":
            taskItems = param.split(" /by ", 2);
            taskName = taskItems[0].strip();

            if (taskItems.length == 1) {
                taskList.add(new Deadline(taskName));
            } else {
                taskList.add(new Deadline(taskName, taskItems[1].strip()));
            }

            output.append(TODO_MESSAGE);
            output.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
            break;
        case "event":
            taskItems = param.split(" /at ", 2);
            taskName = taskItems[0].strip();

            if (taskItems.length == 1) {
                taskList.add(new Event(taskName));
            } else {
                taskList.add(new Event(taskName, taskItems[1].strip()));
            }

            output.append(TODO_MESSAGE);
            output.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
            break;
        case "done":
            int intParam = Integer.parseInt(param) - 1;
            taskList.get(intParam).markAsDone();

            output.append(DONE_MESSAGE);
            output.append(taskList.get(intParam));
            break;
        case "delete":
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
            throw DukeException.invalidInput();
        }
        duke.save(taskList);
        return output.toString();
    }
}
