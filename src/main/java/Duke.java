import duke.Deadline;
import duke.EmptyDescriptionError;
import duke.Event;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Todo;
import duke.Ui;
import duke.UnknownCommandError;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class consists exclusively of methods unique for Duke class.
 *
 * @author Lee Jae Ho
 * @since 0.1
 */

public class Duke implements Runnable {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke class. Initializes Ui, Storage, TaskList classes.
     *
     * @param filePath file URL where task information will be stored.
     */
    public Duke(String filePath) {
        // initialization
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that allows Duke to be run as a program. Uses Scanner to read user commands.
     * Error controls to tackle EmptyDescriptionError and UnknownCommandError. Uses Parser
     * class to classify commands and control code flows. "bye" command will store the current
     * taskList as a duke.txt file and close the scanner.
     */
    public void run() {
        // greet the user
        ui.hi();
        // listen to user input
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String input = scan.nextLine();
            Parser parser = new Parser();
            String command = parser.parse(input);
            switch (command) {
                case "bye":
                    try {
                        storage.store(tasks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ui.bye();
                    scan.close();
                    break;
                case "todo":
                    try {
                        Task t = new Todo(input);
                        tasks.add(t);
                        ui.add(t, tasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        ui.reply(new EmptyDescriptionError("todo").getMessage());
                    }
                    break;
                case "event":
                    try {
                        Task t = new Event(input);
                        tasks.add(t);
                        ui.add(t, tasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        ui.reply(new EmptyDescriptionError("event").getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        Task t = new Deadline(input);
                        tasks.add(t);
                        ui.add(t, tasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        ui.reply(new EmptyDescriptionError("event").getMessage());
                    }
                    break;
                case "list":
                    ui.reply(tasks.list());
                    break;
                case "done":
                    Task t = tasks.done(Integer.parseInt(input.substring(5)) - 1);
                    ui.done(t);
                    break;
                case "delete":
                    t = tasks.delete(Integer.parseInt(input.substring(7)) - 1);
                    ui.delete(t, tasks.size());
                    break;
                case "find":
                    String keyword = input.substring(5);
                    ui.reply(tasks.find(keyword));
                    break;
                default:
                    try {
                        throw new UnknownCommandError("");
                    } catch (UnknownCommandError e) {
                        ui.reply(e.getMessage());
                    }
                    break;
            }
            if (command.equals("bye")) {
                break;
            }
        }
    }

    /**
     * Our main method. It reads the filepath of the duke.txt file, which stores the taskList
     * information, then executes the Duke program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
