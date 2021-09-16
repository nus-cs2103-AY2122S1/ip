package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.gui.Gui;
import duke.utils.CliUi;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Duke extends Application {
    private static final String DATA_FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private CliUi cliUi;
    private boolean isExit = false;

    /**
     * Duke constructor.
     *
     * @param filePath File path of where the saved task list is.
     */
    public Duke(String filePath) {
        cliUi = new CliUi();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            cliUi.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Duke constructor. Loads stored tasks from default filepath.
     */
    public Duke() {
        cliUi = new CliUi();
        storage = new Storage(DATA_FILE_PATH);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            cliUi.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Method to initialize commandline version of Duke.
     */
    public void run() {
        cliUi.showWelcome();
        Command command;
        do {
            try {
                String input = cliUi.readCommand();
                command = Parser.parseNext(input);
                command.execute(taskList, cliUi, storage);
                isExit = command.hasExited();
            } catch (DukeException e) {
                cliUi.printOut(e.getMessage());
            }
        }
        while (!isExit);

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
            response = command.execute(taskList, cliUi, storage);
            isExit = command.hasExited();
        } catch (Exception e) {
            System.out.println(e.toString());
            response = e.getMessage();
        }
        return response;
    }

    @Override
    public void start(Stage stage) {
        Gui gui = new Gui(stage);
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
        gui.setUserInputHandler(mouseEventHandler, buttonEventHandler);
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
