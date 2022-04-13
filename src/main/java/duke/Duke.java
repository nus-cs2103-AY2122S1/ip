package duke;

import java.util.Timer;
import java.util.TimerTask;

import duke.controller.MainWindow;
import duke.task.TaskList;

/**
 * Duke.
 * @author Thomas Hogben
 */
public class Duke {
    private static final long SHUTDOWN_DELAY = 2000;

    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private MainWindow mainWindow;

    public Duke() {
        ui = new Ui(this);
        storage = new Storage(ui);
        try {
            taskList = new TaskList(ui, storage.load());
        } catch (DukeException e) {
            ui.display(e);
            taskList = new TaskList(ui);
        }
        parser = new Parser(taskList, storage, ui);
    }

    public String getResponse(String input) {
        return parser.parse(input);
    }

    public void init(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        ui.init(mainWindow);
    }

    /**
     * Marks the program for shutdown.
     */
    public void exitProgram() {
        mainWindow.setAcceptingInput(false);

        TimerTask shutdown = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        new Timer().schedule(shutdown, SHUTDOWN_DELAY);
    }
}
