package duke.parser;

import java.util.List;

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
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String DONE_MESSAGE = "Nice! I've marked this duke.task as done:";
    private static final String TODO_MESSAGE = "Got it. I've added this duke.task:";
    private static final String DELETE_MESSAGE = "Noted. I've removed this duke.task:";
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:";

    /**
     * Turns the user's input into a command.
     *
     * @param userInput the user's input given as a string.
     * @param taskList  the list containing the tasks.
     * @throws DukeException if the inputs are not expected.
     */
    public static void parse(String userInput, TaskList taskList) throws DukeException {
        String[] tokens = userInput.split("\\s+", 2);
        String command = tokens[0];
        String param = tokens.length == 1 ? null : tokens[1].strip();

        String[] taskItems;
        String taskName;
        Task task;

        switch (command) {
        case "todo":
            if (param == null) {
                throw DukeException.emptyDescription();
            }

            System.out.println(TODO_MESSAGE);
            task = new Todo(param);
            taskList.add(task);
            System.out.println(taskList.get(taskList.getSize() - 1));
            System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
            break;
        case "list":
            System.out.println(LIST_MESSAGE);
            List<String> enumerate = taskList.enumerate();
            enumerate.forEach(System.out::println);
            System.out.println("There are currently " + taskList.getSize() + " tasks in your list.");
            break;
        case "find":
            System.out.println(FIND_MESSAGE);
            enumerate = taskList.filter(param).enumerate();
            enumerate.forEach(System.out::println);
            break;
        case "deadline":
            System.out.println(TODO_MESSAGE);

            taskItems = param.split(" /by ", 2);
            taskName = taskItems[0].strip();

            if (taskItems.length == 1) {
                taskList.add(new Deadline(taskName));
            } else {
                taskList.add(new Deadline(taskName, taskItems[1].strip()));
            }
            System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
            break;
        case "event":
            System.out.println(TODO_MESSAGE);
            taskItems = param.split(" /at ", 2);
            taskName = taskItems[0].strip();

            if (taskItems.length == 1) {
                taskList.add(new Event(taskName));
            } else {
                taskList.add(new Event(taskName, taskItems[1].strip()));
            }
            System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
            break;
        case "done":
            System.out.println(DONE_MESSAGE);
            int intParam = Integer.parseInt(param) - 1;
            taskList.get(intParam).markAsDone();
            System.out.println(taskList.get(intParam));
            break;
        case "delete":
            System.out.println(DELETE_MESSAGE);
            intParam = Integer.parseInt(param) - 1;
            System.out.println(taskList.get(intParam));
            taskList.remove(intParam);
            System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
            break;
        case "bye":
            System.out.println(BYE_MESSAGE);
            break;
        default: // Adds duke.task
            throw DukeException.invalidInput();
        }
    }
}
