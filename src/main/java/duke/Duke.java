package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * Represents the main class for duke.Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a duke.Duke object.
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
     * Constructs a duke.Duke object.
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
                output = handleBye(output);
                break;
            case "list":
                output = handleList(output);
                break;
            case "done":
                output = handleDone(output, inputWords);
                break;
            case "todo":
                output = handleTodo(output, inputWords);
                break;
            case "deadline":
                output = handleDeadline(output, inputWords);
                break;
            case "event":
                output = handleEvent(output, inputWords);
                break;
            case "delete":
                output = handleDelete(output, inputWords);
                break;
            case "find":
                output = handleFind(output, inputWords);
                break;
            case "snooze":
                output = handleSnooze(output, inputWords);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            output = handleException(e, output);
        }
        return output;
    }

    private String handleList(String output) {
        output += tasks.getAllTasksString();
        return output;
    }

    private String handleBye(String output) {
        output += "Bye, hope to see you again!";
        return output;
    }

    private String handleDone(String output, String inputWords[]) throws DukeException, IOException {
        if (inputWords.length == 1) {
            throw new DukeException("Please provide a task ID that exists.");
        }
        int taskId = Integer.parseInt(inputWords[1]);
        assert output == "";
        output = tasks.markTaskAsDone(taskId);
        storage.saveFile(tasks.getAllTasksString());
        return output;
    }

    private String handleTodo(String output, String[] inputWords) throws DukeException, IOException {
        if (inputWords.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        assert output == "";
        output = tasks.addTodo(inputWords[1]);
        storage.saveFile(tasks.getAllTasksString());
        return output;
    }

    private String handleDeadline(String output, String[] inputWords) throws DukeException, IOException {
        if (inputWords.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        assert output == "";
        output = tasks.addDeadline(inputWords[1]);
        storage.saveFile(tasks.getAllTasksString());
        return output;
    }

    private String handleEvent(String output, String[] inputWords) throws DukeException, IOException {
        if (inputWords.length == 1) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        assert output == "";
        output = tasks.addEvent(inputWords[1]);
        storage.saveFile(tasks.getAllTasksString());
        return output;
    }

    private String handleDelete(String output, String[] inputWords) throws DukeException, IOException {
        if (inputWords.length == 1) {
            throw new DukeException("Please provide a task ID that exists.");
        }
        int taskId = Integer.parseInt(inputWords[1]);
        assert output == "";
        output = tasks.deleteTask(taskId);
        storage.saveFile(tasks.getAllTasksString());
        return output;
    }

    private String handleFind(String output, String[] inputWords) throws DukeException {
        if (inputWords.length != 2) {
            throw new DukeException("Please provide a only one keyword.");
        }
        assert output == "";
        output = tasks.findTask(inputWords[1]);
        return output;
    }

    private String handleSnooze(String output, String[] inputWords) throws DukeException, IOException {
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
        return output;
    }

    private String handleException(Exception e, String output) {
        if (e instanceof DateTimeException) {
            output += "Please provide date/time in the correct format: yyyy-mm-dd HH:mm, where" +
                    "time is optional.";
        } else {
            output += e.getMessage();
        }
        return output;
    }
}

