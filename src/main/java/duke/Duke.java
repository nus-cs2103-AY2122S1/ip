package duke;

import command.Command;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Duke extends Application {
    /** Duke's Storage. Deals with loading tasks from the file and saving tasks in the file. **/
    private final Storage storage;

    /** Duke's TaskList. Containing the data structure for storing tasks. **/
    private TaskList taskList;

    /** Duke's user interface. Deals with interactions with the user. **/
    private final Ui ui;

    /**
     * A public constructor to initialized Duke.
     */
    public Duke() {
        ui = new Ui(this);
        storage = new Storage("data/taskList.txt");
        try {
            taskList = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            ui.showDukeException(e);
            taskList = new TaskList();
        }
    }

    /**
     * A public constructor to initialized Duke.
     *
     * @param filePath The given filePath to load and save the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui(this);
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            ui.showDukeException(e);
            taskList = new TaskList();
        }
    }

    /**
     * The method to execute Duke.
     */
    public void run() {
        ui.printLogo();
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showDukeException(e);
            }
        }
    }

    /**
     * Main method of Duke.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        System.out.println("main");
        new Duke("data/taskList.txt").run();
    }

    /**
     * A method to get Duke response.
     *
     * @param input The given user input.
     */
    protected void getResponse(String input) throws DukeException {
        Command c = Parser.parse(input);
        c.execute(taskList, ui, storage);
        if (c.isExit()) {
            System.exit(0);
        }
    }

    /**
     * A method to start Duke.
     *
     * @param stage The given stage.
     */
    @Override
    public void start(Stage stage) {
        ui.start(stage);
    }
}
