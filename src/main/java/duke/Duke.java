package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.DateTimeException;

/**
 * Represents the main class for duke.Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for duke.Duke class.
     *
     * @param dirPath  Directory path of the file.
     * @param fileName Name of the file.
     */
    public Duke(String dirPath, String fileName) {
        try {
            storage = new Storage(dirPath, fileName);
            tasks = new TaskList(storage.loadFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructor for duke.Duke class.
     */

    public Duke() {
        this("./data/", "duke.txt");
    }

    /**
     * Handles user input.
     *
     * @return Response to user input.
     */
    public String getResponse(String input) {
        String output = "";
        String[] inputWords = null;

        inputWords = Parser.parse(input);
        try {
            switch (inputWords[0]) {
            case "bye":
                System.out.println("Bye, hope to see you again!");
                output += "Bye, hope to see you again!";
                break;
            case "list":
                output += tasks.getAllTasksString();
                break;
            case "done": {
                if (inputWords.length == 1) {
                    throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
                }
                int taskId = Integer.parseInt(inputWords[1]);
                output = tasks.markTaskAsDone(taskId);
                storage.saveFile(tasks.getAllTasksString());
                break;
            }
            case "todo":
                if (inputWords.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                output = tasks.addTodo(inputWords[1]);
                storage.saveFile(tasks.getAllTasksString());
                break;
            case "deadline":
                if (inputWords.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                output = tasks.addDeadline(inputWords[1]);
                storage.saveFile(tasks.getAllTasksString());
                break;
            case "event":
                if (inputWords.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                output = tasks.addEvent(inputWords[1]);
                storage.saveFile(tasks.getAllTasksString());
                break;
            case "delete":
                if (inputWords.length == 1) {
                    throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
                }
                int taskId = Integer.parseInt(inputWords[1]);
                output = tasks.deleteTask(taskId);
                storage.saveFile(tasks.getAllTasksString());
                break;
            case "find":
                if (inputWords.length != 2) {
                    throw new DukeException("☹ OOPS!!! Please provide a only one keyword.");
                }
                output = tasks.findTask(inputWords[1]);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DateTimeException e) {
            Ui.printError("Please provide date/time in the correct format: yyyy-mm-dd HH:mm, where" +
                    "time is optional.");
            output += "Please provide date/time in the correct format: yyyy-mm-dd HH:mm, where" +
                    "time is optional.";
        } catch (Exception e) {
            Ui.printError(e.getMessage());
            output += e.getMessage();
        }
        return output;
    }
}

