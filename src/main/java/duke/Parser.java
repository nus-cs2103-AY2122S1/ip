package duke;

import java.io.IOException;

import duke.tasks.TaskList;
import javafx.application.Platform;

/**
 * Parses the user's input.
 */
public class Parser {
    public static final String HI_STRING = "Hi, I am BMO! Who wants to play video games?";
    public static final String ME_STRING = "Bad user! Input some tasks now to be productive!";
    public static final String HELP_STRING = "Try one of these commands to begin: todo, event, deadline, update, " +
            "done, find, delete, list";
    public static final String BYE_STRING = "Beep boop! Battery low. Commencing shutdown!";

    public Parser() {
    }

    /**
     * Parses the user input and returns a String.
     * @param task user input
     * @return response to user input
     * @throws DukeException exception
     * @throws IOException exception
     */
    public String parseTask(String task) throws DukeException, IOException {
        assert task.length() > 1;
        switch (task) {
        case "list": return TaskList.printList();
        case "bye":
            Platform.exit();
            return BYE_STRING;
        case "hi": return HI_STRING;
        case "me": return ME_STRING;
        case "help": return HELP_STRING;
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
