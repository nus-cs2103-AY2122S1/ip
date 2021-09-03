package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.gui.Gui;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Duke extends Application {
    // TODO -> refactor out gui related stuff
    private static final String DATA_FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Duke constructor.
     *
     * @param filePath File path of where the saved task list is.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Duke constructor. Loads stored tasks from default filepath.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(DATA_FILE_PATH);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Method to initialize commandline version of Duke.
     */
    public void run() {
        ui.showWelcome();
        Command command;
        boolean isExited = false;
        do {
            try {
                String input = ui.readCommand();
                command = Parser.parseNext(input);
                command.execute(taskList, ui, storage);
                isExited = command.hasExited();
            } catch (DukeException e) {
                ui.printOut(e.getMessage());
            }
        }
        while (!isExited);


    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        Command command;
        String response;
        try {
            command = Parser.parseNext(input);
            response = command.execute(taskList, ui, storage);
            isExit = command.hasExited();
        } catch (Exception e) {
            System.out.println(e.toString());
            response = e.getMessage();
        }

        return response;
    }

    @Override
    public void start(Stage stage) {

        Gui gui = new Gui();
        gui.setup(stage);
        EventHandler<? super MouseEvent> mouseEventHandler;
        EventHandler<ActionEvent> buttonEventHandler;

        mouseEventHandler = e -> {
            String input = gui.getUserInput();
            gui.generateDialogBoxes(getResponse(input));
            if (isExit) {
                gui.exit();
            }
        };
        buttonEventHandler = e -> {
            String input = gui.getUserInput();
            gui.generateDialogBoxes(getResponse(input));
            if (isExit) {
                gui.exit();
            }
        };

        gui.setHandleUserInput(mouseEventHandler, buttonEventHandler);
    }

    /**
     * Main method for Duke.
     *
     * @param args Placeholder argument.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }
}
