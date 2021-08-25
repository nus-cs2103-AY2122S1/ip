package duke.parser;

import duke.DukeException;
import duke.task.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Parser {
    private static final DateTimeFormatter DATE_TIME_PARSE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    static String byeMsg = "Bye. Hope to see you again soon!";
    static String listMsg = "Here are the tasks in your list:";
    static String doneMsg = "Nice! I've marked this duke.task as done:";
    static String todoMsg = "Got it. I've added this duke.task:";
    static String deleteMsg = "Noted. I've removed this duke.task:";

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

            System.out.println(todoMsg);
            task = new Todo(param);
            taskList.add(task);
            System.out.println(taskList.get(taskList.getSize() - 1));
            System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
            break;
        case "list":
            System.out.println(listMsg);
            List<String> enumerate = taskList.enumerate();
            enumerate.forEach(System.out::println);
            System.out.println("There are currently " + taskList.getSize() + " tasks in your list.");
            break;
        case "deadline":
            System.out.println(todoMsg);

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
            System.out.println(todoMsg);
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
            System.out.println(doneMsg);
            int intParam = Integer.parseInt(param) - 1;
            taskList.get(intParam).markAsDone();
            System.out.println(taskList.get(intParam));
            break;
        case "delete":
            System.out.println(deleteMsg);
            intParam = Integer.parseInt(param) - 1;
            System.out.println(taskList.get(intParam));
            taskList.remove(intParam);
            System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
            break;
        case "bye":
            System.out.println(byeMsg);
            break;
        default: // Adds duke.task
            throw DukeException.invalidInput();
        }
    }
}
