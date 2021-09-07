package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
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
                    throw new DukeException("Please provide a task ID that exists.");
                }
                int taskId = Integer.parseInt(inputWords[1]);
                assert output == "";
                output = tasks.markTaskAsDone(taskId);
                storage.saveFile(tasks.getAllTasksString());
                break;
            }
            case "todo":
                if (inputWords.length == 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                assert output == "";
                output = tasks.addTodo(inputWords[1]);
                storage.saveFile(tasks.getAllTasksString());
                break;
            case "deadline":
                if (inputWords.length == 1) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                assert output == "";
                output = tasks.addDeadline(inputWords[1]);
                storage.saveFile(tasks.getAllTasksString());
                break;
            case "event":
                if (inputWords.length == 1) {
                    throw new DukeException("The description of a event cannot be empty.");
                }
                assert output == "";
                output = tasks.addEvent(inputWords[1]);
                storage.saveFile(tasks.getAllTasksString());
                break;
            case "delete": {
                if (inputWords.length == 1) {
                    throw new DukeException("Please provide a task ID that exists.");
                }
                int taskId = Integer.parseInt(inputWords[1]);
                assert output == "";
                output = tasks.deleteTask(taskId);
                storage.saveFile(tasks.getAllTasksString());
                break;
            }
            case "find":
                if (inputWords.length != 2) {
                    throw new DukeException("Please provide a only one keyword.");
                }
                assert output == "";
                output = tasks.findTask(inputWords[1]);
                break;
            case "snooze":
                String[] taskIdAndDays = inputWords[1].split(" ");
                if (taskIdAndDays.length != 2) {
                    throw new DukeException("Please provide the command in the correct format:" +
                            " snooze {id} {number of days to snooze}.");
                }
                int taskId = Integer.parseInt(taskIdAndDays[0]);
                int days = Integer.parseInt(taskIdAndDays[1]);
                assert output == "";
                output = tasks.increaseTaskDateByDays(taskId, days);
                storage.saveFile(tasks.getAllTasksString());
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
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

