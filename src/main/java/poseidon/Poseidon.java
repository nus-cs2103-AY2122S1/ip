package poseidon;

import java.io.IOException;
import java.time.LocalDateTime;

import poseidon.exception.PoseidonException;
import poseidon.parser.Parser;
import poseidon.storage.Storage;
import poseidon.task.Deadline;
import poseidon.task.Event;
import poseidon.task.Task;
import poseidon.task.Todo;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;


/**
 * Represents a {@code Poseidon} object that executes the user commands.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Poseidon {

    private static final String NON_EXISTENT_TASK_MSG = "That task doesn't exist.\nPlease Try again.";
    private static final String BYE_CMD = "bye";
    private static final String LIST_CMD = "list";
    private static final String ADD_CMD = "add";
    private static final String DONE_CMD = "done";
    private static final String DELETE_CMD = "delete";
    private static final String FIND_CMD = "find";
    private static final String SORT_CMD = "sort";
    private static final String FAIL_CMD = "fail";
    private static final String ADD_TODO_CMD = "todo";
    private static final String ADD_DEADLINE_CMD = "deadline";
    private static final String ADD_EVENT_CMD = "event";
    private static final String HELP_CMD = "help";

    /** {@code Storage} object that reads from and writes onto the hard disk  */
    private Storage storage;

    /** {@code TaskList} object that maintains and updates the list of tasks */
    private TaskList taskList;

    /** {@code Ui} object to be used to generate responses to User commands */
    private Ui ui;

    /**
     * Constructs a {@code Poseidon} object and initialises the class members.
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
     * Returns a {@code String} representation of a welcome message as prepared by {@code Ui} class.
     *
     * @return {@code String} welcome message.
     */
    public String runWelcome() {
        return ui.getWelcomeMessage();
    }

    /**
     * Returns a {@code String} representation of the Bot's response to the given new User command.
     *
     * @param newCommand {@code String} version of the User input.
     * @return {@code String} response.
     */
    public String run(String newCommand) {
        String errorMessage = "";
        try {
            String[] parsedCommand = Parser.parse(newCommand);
            switch (parsedCommand[0]) {
            case HELP_CMD:
                return ui.showHelp();
            case BYE_CMD:
                return ui.showGoodbye();
            case LIST_CMD:
                return ui.getListMessage(taskList.getList());
            case ADD_CMD:
                return addTask(parsedCommand);
            case DONE_CMD:
                return markTaskDone(parsedCommand);
            case DELETE_CMD:
                return deleteTask(parsedCommand);
            case FIND_CMD:
                return ui.showFindList(taskList.findTasks(parsedCommand[1]));
            case SORT_CMD:
                return ui.getListMessage(taskList.sortTasks());
            case FAIL_CMD:
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
     * Returns true if the given user input is a "bye" command.
     *
     * @param newCommand {@code String} version of the user input.
     * @return {@code Boolean} validation result.
     */
    public boolean isBye(String newCommand) {
        return Parser.isParsedBye(newCommand);
    }

    private String addTask(String[] parsedCommand) throws IOException {
        Task newTask = null;

        switch (parsedCommand[1]) {
        case ADD_TODO_CMD:
            newTask = new Todo(parsedCommand[2]);
            break;
        case ADD_DEADLINE_CMD:
            LocalDateTime by = Parser.parseDateTime(parsedCommand[3]);
            newTask = new Deadline(parsedCommand[2], by);
            break;
        case ADD_EVENT_CMD:
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
            throw new PoseidonException(NON_EXISTENT_TASK_MSG);
        }

        String message = taskList.markTaskDone(taskIndex);
        String taskStorage = taskList.getTaskStorage(taskIndex);
        storage.storeDone(taskIndex, taskStorage);
        return ui.showMessage(message);
    }

    private String deleteTask(String[] parsedCommand) throws IOException {
        int taskIndex = Parser.parseIndex(parsedCommand[1]);

        if (!taskList.isIndexValid(taskIndex)) {
            throw new PoseidonException(NON_EXISTENT_TASK_MSG);
        }

        String message = taskList.deleteTask(taskIndex);
        storage.storeDelete(taskIndex);
        return ui.showMessage(message);
    }
}
