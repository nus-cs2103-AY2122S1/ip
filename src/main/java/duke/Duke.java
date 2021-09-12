package duke;

import java.io.IOException;
import java.util.ArrayList;

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
    private TaskList items;
    // checking if user still wants to input
    private boolean isRunning;
    private Undo undo;
    private String[] prevCommand;
    // boolean to check if last task is undo-able
    private boolean isUndoable = false;

    private String deletedTask;

    /**
     * Duke Constructor
     */
    public Duke(String directory, String file) {
        ui = new Ui();
        storage = new Storage(directory, file);
        parser = new Parser();
        isRunning = true;
        try {
            items = new TaskList(storage.loadData(), storage);
        } catch (DukeException e) {
            items = new TaskList();
        }
        this.undo = new Undo(this.items);
    }

    /**
     * Runs the duke chatbot.
     */
    public void run() {
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
                isUndoable = false;
                break;
            case "done":
                int idx = Integer.parseInt(inputWords[1]);
                output = items.markDone(idx);
                fileTask = storage.getFileLine(idx);
                fileTask = fileTask.substring(0, 4) + "1" + fileTask.substring(5);
                storage.updateListTask(idx, fileTask);
                isUndoable = true;
                break;
            case "bye":
                isRunning = false;
                output = "Going so soon? I'll be waiting for you.";
                break;
            case "todo":
                output = items.addItem(new Todo(task[0]));
                fileTask = "T | 0 | " + task[0];
                storage.addToFile(fileTask);
                isUndoable = true;
                break;
            case "event":
                output = items.addItem(new Event(task[0], task[1]));
                fileTask = "E | 0 | " + task[0] + " | " + task[1];
                storage.addToFile(fileTask);
                isUndoable = true;
                break;
            case "deadline":
                output = items.addItem(new Deadline(task[0], task[1]));
                fileTask = "D | 0 | " + task[0] + " | " + task[1];
                storage.addToFile(fileTask);
                isUndoable = true;
                break;
            case "delete":
                int id = Integer.parseInt(inputWords[1]);
                deletedTask = items.getTaskAtIndex(id);
                output = items.deleteItem(id);
                storage.deleteFromFile(id);
                isUndoable = true;
                break;
            case "find":
                output = items.findTask(task[0]);
                isUndoable = false;
                break;
            case "undo":
                if (!isUndoable) {
                    output = "Please implement a valid undo-able task so I can undo!\n"
                            + "'todo', 'event', 'deadline', 'done' and 'delete' can be undone";
                } else {
                    output = undoLogic(prevCommand);
                }
                break;
            case "help":
                output = ui.getHelpMenu();
                isUndoable = false;
                break;
            default:
                isUndoable = false;
                output = "I don't recognise this command\n"
                        + "Try 'list', 'todo', 'event', 'deadline', 'done', 'find', 'undo' or 'bye'";
                break;
            }
            assert !output.equals(""): "Unable to generate response. Please try again.";
            prevCommand = inputWords;
            return output;
        } catch (Exception dukeException) {
            return dukeException.getMessage();
        }

    }

    /**
     * Undoes the last command
     *
     * @param undoCommand the command to be undone
     * @return output after undoing the latest task
     * @throws DukeException
     * @throws IOException
     */
    private String undoLogic(String[] undoCommand) throws DukeException, IOException {
        String output;
        String fileTask;
        String inputCommand = undoCommand[0];
        switch (inputCommand) {
        case "delete":
            int index = Integer.parseInt(prevCommand[1]);
            output = undo.undoDelete(index, deletedTask);
            storage.addToFile(index, deletedTask);
            break;
        case "done":
            int taskIndex = Integer.parseInt(undoCommand[1]);
            fileTask = undo.undoDone(taskIndex);
            storage.updateListTask(taskIndex, fileTask);
            output = "The following task has been marked as 'Not Done':\n" + fileTask;
            break;
        case "deadline":
        case "event":
        case "todo":
            fileTask = undo.deleteTask();
            storage.deleteFromFile(items.getListSize() - 1);
            output = "Following task has been removed:\n" + fileTask;
            break;
        default:
            output = "Only undo has been implemented";
        }
        isUndoable = false;
        return output;
    }

    /**
     * Returns true when duke is awake and false otherwise.
     *
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
