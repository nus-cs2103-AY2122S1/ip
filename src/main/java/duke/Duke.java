package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Duke class to store a list of tasks, which you can add upon.
 * The 3 different tasks include event, todo, and deadline.
 * Duke supports functions such as done, delete, find, and list too.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final String filePath = "data/tasks.txt";
    private List<Storage> storageList;
    private List<Ui> uiList;
    private List<TaskList> tasksList;


    /**
     * Constructs Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
        storageList = new ArrayList<>();
        uiList = new ArrayList<>();
        tasksList = new ArrayList<>();
        assert tasks != null : "Task should not be null";
    }

    /**
     * Setting new Storage, TaskList, and Ui.
     *
     * @param storage New Storage set.
     * @param tasks New TaskList set.
     * @param ui New Ui set.
     * @param storageList List of past storages.
     * @param uiList List of past Ui.
     * @param tasksList List of past TaskLists.
     */
    public void setElements(Storage storage
            , TaskList tasks
            , Ui ui
            , List<Storage> storageList
            , List<Ui> uiList
            , List<TaskList> tasksList) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
        this.storageList = storageList;
        this.uiList = uiList;
        this.tasksList = tasksList;
    }

    /**
     * Runs the Duke program after reading input by user.
     *
     * @param input User's input.
     * @return String to confirm the user's input.
     */
    public String run(String input) {
        if (!storage.isExit()) {
            try {
                Parser p = new Parser(input, ui, storage, tasks, storageList, uiList, tasksList, this);
                return p.parseCommand();
            } catch (DeleteException | DukeException | IOException | StringIndexOutOfBoundsException | FindException
                    | NumberFormatException | DoneException | UndoException | CloneNotSupportedException e) {
                return e.getMessage();
            }
        }
        return "You have terminated your program, please exit and re-enter";
    }

    /**
     * Starts the Gui.
     *
     * @param stage The stage to initialise Gui.
     */
    @Override
    public void start(Stage stage) {
        Duke duke = new Duke();
        ui.initialiseGui(stage, duke);
    }
}
