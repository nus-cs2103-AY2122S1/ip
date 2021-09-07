package duke;

import java.io.IOException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;



/**
 * Class to handle all duke logic.
 */
public class Duke {

    // All UI functionality
    private final Ui ui;
    // All saved data related functionality
    private final Storage storage;
    // All user input related functionality
    private final Parser parser;
    // the list of items
    private Items items;
    // checking if user still wants to input
    private boolean isRunning;

    /**
     * Duke Constructor
     */
    public Duke(String directory, String file) {
        ui = new Ui();
        storage = new Storage(directory, file);
        parser = new Parser();
        isRunning = true;
        try {
            items = new Items(storage.loadData());
        } catch (DukeException e) {
            items = new Items();
        }
    }

    /**
     * Runs the duke chatbot.
     *
     * @throws DukeException in case any unexpected input is passed.
     * @throws IOException in case of file issues.
     */
    public void run() throws DukeException, IOException {
        ui.greet();
        while (isRunning) {
            getResponse(ui.getInput());
        }
        ui.printGoodBye();
    }

    /**
     * Interacts with the user.
     */
    public String getResponse(String input) {
        String[] inputWords;
        String output = "";
        String fileTask;
        inputWords = input.split("\\s+");

        if (inputWords.length == 0) {
            output = "Sorry, I don't understand empty statements.";
            return output;
        }
        String command = inputWords[0];

        assert command != null : "Command cannot be NULL";
        try {
            String[] task = parser.compileInput(inputWords);
            switch (command) {
            case "list":
                output = items.printList();
                break;
            case "done":
                int idx = Integer.parseInt(inputWords[1]);
                output = items.markDone(idx);
                fileTask = storage.getFileLine(idx);
                fileTask = fileTask.substring(0, 4) + "1" + fileTask.substring(5);
                storage.updateListTask(idx, fileTask);
                break;
            case "bye":
                isRunning = false;
                output = "Going so soon? I'll be waiting for you.";
                break;
            case "todo":
                output = items.addItem(new Todo(task[0]));
                fileTask = "T | 0 | " + task[0];
                storage.addToFile(fileTask);
                break;
            case "event":
                output = items.addItem(new Event(task[0], task[1]));
                fileTask = "E | 0 | " + task[0] + " | " + task[1];
                storage.addToFile(fileTask);
                break;
            case "deadline":
                output = items.addItem(new Deadline(task[0], task[1]));
                fileTask = "D | 0 | " + task[0] + " | " + task[1];
                storage.addToFile(fileTask);
                break;
            case "delete":
                int id = Integer.parseInt(inputWords[1]);
                output = items.deleteItem(id);
                storage.deleteFromFile(id);
                break;
            case "find":
                output = items.findTask(task[0]);
                break;
            default:
                output = "I don't recognise this command\n"
                        + "Try 'list', 'todo', 'event', 'deadline', 'done', 'find' or 'bye'";
                break;
            }
            assert !output.equals(""): "Unable to generate response. Please try again.";
            return output;
        } catch (Exception dukeException) {
            return dukeException.getMessage();
        }
    }

    /**
     * Returns true when duke is awake and false otherwise.
     * @return True when duke is awake and false otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * The main function of Bhutu.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke("./data", "duke.txt").run();
    }
}
