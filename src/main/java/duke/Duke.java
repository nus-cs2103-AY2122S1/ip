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
     * Starts interaction with the user.
     */
    public void run() {
        String[] inputWords = null;
        Ui.greet();
        do {
            inputWords = Parser.parse(Ui.readCommand());
            try {
                switch (inputWords[0]) {
                case "bye":
                    break;
                case "list":
                    tasks.printTasks();
                    break;
                case "done": {
                    if (inputWords.length == 1) {
                        throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
                    }
                    int taskId = Integer.parseInt(inputWords[1]);
                    tasks.markTaskAsDone(taskId);
                    storage.saveFile(tasks.tasksAsString());
                    break;
                }
                case "todo":
                    if (inputWords.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.addTodo(inputWords[1]);
                    storage.saveFile(tasks.tasksAsString());
                    break;
                case "deadline":
                    if (inputWords.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    tasks.addDeadline(inputWords[1]);
                    storage.saveFile(tasks.tasksAsString());
                    break;
                case "event":
                    if (inputWords.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    tasks.addEvent(inputWords[1]);
                    storage.saveFile(tasks.tasksAsString());
                    break;
                case "delete":
                    if (inputWords.length == 1) {
                        throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
                    }
                    int taskId = Integer.parseInt(inputWords[1]);
                    tasks.deleteTask(taskId);
                    storage.saveFile(tasks.tasksAsString());
                    break;
                case "find":
                    if (inputWords.length != 2) {
                        throw new DukeException("☹ OOPS!!! Please provide a only one keyword.");
                    }
                    tasks.findTask(inputWords[1]);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DateTimeException e) {
                Ui.printError("Please provide date/time in the correct format: yyyy-mm-dd HH:mm");
            } catch (Exception e) {
                Ui.printError(e.getMessage());
            }
        }
        while (!inputWords[0].equals("bye"));
        System.out.println("Bye, hope to see you again!");
    }

    /**
     * Creates an instance of duke.Duke and starts the user interaction.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/", "duke.txt").run();
    }
}

