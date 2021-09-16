package poseidon;

import java.io.IOException;
import java.time.LocalDateTime;

import poseidon.task.Deadline;
import poseidon.task.Event;
import poseidon.task.Task;
import poseidon.task.Todo;


public class Poseidon {

    private static final String NON_EXISTENT_TASK = "That task doesn't exist.\nPlease Try again.";

    /** Storage object that reads from and writes onto the hard disk  */
    private Storage storage;

    /** TaskList object that maintains and updates the list of tasks */
    private TaskList taskList;

    /** Ui object to be used to interact with the user via the command line interface */
    private Ui ui;

    /**
     * Constructs a Poseidon object and initialises the class members.
     */
    public Poseidon() {
        ui = new Ui();
        try {
            storage = new Storage();
            taskList = new TaskList(storage.load());
        } catch (PoseidonException ex) {
            ui.showStorageError();
            storage = null;
            taskList = new TaskList();
        }
    }

    /**
     * Returns a string representation of a welcome message as prepared by Ui class.
     *
     * @return String welcome message.
     */
    public String runWelcome() {
        return ui.getWelcomeMessage();
    }

    /**
     * Returns a string representation of the bot's response to the given new user command.
     *
     * @param newCommand String version of the user input.
     * @return String response.
     */
    public String run(String newCommand) {
        String errorMessage = "";
        try {
            String[] parsedCommand = Parser.parse(newCommand);
            switch (parsedCommand[0]) {
            case "bye":
                return ui.showGoodbye();
            case "list":
                return ui.getListMessage(taskList.getList());
            case "add":
                return addTask(parsedCommand);
            case "done":
                return markTaskDone(parsedCommand);
            case "delete":
                return deleteTask(parsedCommand);
            case "find":
                return ui.showFindList(taskList.findTasks(parsedCommand[1]));
            case "sort":
                return ui.getListMessage(taskList.sortTasks());
            case "fail":
                return ui.showCommandFail();
            default:
                break;
            }
        } catch (PoseidonException | IOException ex) {
            errorMessage = ui.showError(ex.getMessage());
        }
        assert errorMessage.length() != 0 : "Error message supposed to contain readable text";
        return errorMessage;
    }

    /**
     * Returns true is the given user input is a "bye" command.
     *
     * @param newCommand String version of the user input.
     * @return Boolean validation result.
     */
    public boolean isBye(String newCommand) {
        return Parser.isParsedBye(newCommand);
    }

    private String addTask(String[] parsedCommand) throws IOException {
        Task newTask = null;

        switch (parsedCommand[1]) {
        case "todo":
            newTask = new Todo(parsedCommand[2]);
            break;
        case "deadline":
            LocalDateTime by = Parser.parseDateTime(parsedCommand[3]);
            newTask = new Deadline(parsedCommand[2], by);
            break;
        case "event":
            LocalDateTime from = Parser.parseDateTime(parsedCommand[3]);
            LocalDateTime to = Parser.parseDateTime(parsedCommand[3]);
            newTask = new Event(parsedCommand[2], from, to);
            break;
        default:
            break;
        }

        if (newTask == null) {
            return ui.showCommandFail();
        }

        storage.storeAdd(newTask.toStorage());
        String message = taskList.addTask(newTask);
        return ui.showMessage(message);
    }

    private String markTaskDone(String[] parsedCommand) throws IOException {
        int taskIndex = Parser.parseIndex(parsedCommand[1]);

        if (!taskList.isIndexValid(taskIndex)) {
            throw new PoseidonException(NON_EXISTENT_TASK);
        }

        String message = taskList.markTaskDone(taskIndex);
        String taskStorage = taskList.getTaskStorage(taskIndex);
        storage.storeDone(taskIndex, taskStorage);
        return ui.showMessage(message);
    }

    private String deleteTask(String[] parsedCommand) throws IOException {
        int taskIndex = Parser.parseIndex(parsedCommand[1]);

        if (!taskList.isIndexValid(taskIndex)) {
            throw new PoseidonException(NON_EXISTENT_TASK);
        }

        String message = taskList.deleteTask(taskIndex);
        storage.storeDelete(taskIndex);
        return ui.showMessage(message);
    }
}
