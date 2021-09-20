package duke;
import duke.tasks.TaskList;
import java.io.IOException;

/**
 * Parses the user's input.
 */
public class Parser {

    public Parser() {
    }

    public String parseTask(String task) throws DukeException, IOException {
        assert task.length() > 0;
        if (task.equals("list")) {
            return TaskList.printList();
        } else if (task.startsWith("done")) {
            String str = TaskList.complete(task);
            Storage.overwrite();
            return str;
        } else if (task.startsWith("find")) {
            String[] input = task.split("find ", 2);
            return TaskList.find(input[1]);
        } else if (task.startsWith("deadline") || task.startsWith("event") || task.startsWith("todo")) {
            switch (task) {
            case "deadline":
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            case "event":
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            case "todo":
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            default:
                return Storage.writeToFile(task);
            }
        } else if (task.startsWith("delete")) {
            String numString = task.replaceAll("[^0-9]", "");
            int num = Integer.parseInt(numString);
            String str = TaskList.deleteTask(num);
            Storage.overwrite();
            return str;
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
