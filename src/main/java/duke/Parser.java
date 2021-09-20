package duke;
import duke.tasks.TaskList;
import java.io.IOException;
import javafx.application.Platform;

/**
 * Parses the user's input.
 */
public class Parser {

    public Parser() {
    }

    public String parseTask(String task) throws DukeException, IOException {
        assert task.length() > 1;
        switch (task) {
            case "list": return TaskList.printList();
            case "bye":
                String str = "Beep boop! Battery low. Commencing shutdown!";
                Platform.exit();
                return str;
            case "hi": return "Hi, I am BMO! Who wants to play video games?";
            case "me": return "Bad user! Input some tasks now to be productive!";
            default: break;
        }
        if (task.startsWith("done")) {
            String str = TaskList.complete(task);
            Storage.overwrite();
            return str;
        } else if (task.startsWith("find")) {
            String[] input = task.split("find ", 2);
            return TaskList.find(input[1]);
        } else if (task.startsWith("update")) {
            String[] input = task.split("update ", 2);
            String text = TaskList.update(input[1]);
            Storage.overwrite();
            return text;
        } else if (task.startsWith("deadline") || task.startsWith("event") || task.startsWith("todo")) {
            switch (task) {
            case "deadline":
                throw new DukeException("Invalid input! The description of a deadline cannot be empty.");
            case "event":
                throw new DukeException("Invalid input! The description of a event cannot be empty.");
            case "todo":
                throw new DukeException("Invalid input! The description of a todo cannot be empty.");
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
            throw new DukeException("You have entered an invalid input! :-(");
        }
    }
}
