package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Gui;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * A chatbot based on Project Duke
 *
 * @author KelvinSoo
 * @version Level-10
 */
public class Duke extends Application{

    private final Storage storage;
    private TaskList taskList;
    private final Gui gui;

    /**
     * A constructor to initialize a chatbot.
     */
    public Duke() {
        gui = new Gui();
        storage = new Storage("./data/duke.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            gui.showResponse(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Start a new chatbot session.
     * @param stage the stage.
     */
    @Override
    public void start(Stage stage) {
        gui.start(stage);
        Button sendButton = gui.getButton();
        TextField userTextField = gui.getUserTextField();

        sendButton.setOnMouseClicked((event) -> {
            handleOnClickEvent();
        });

        userTextField.setOnAction((event) -> {
            handleOnClickEvent();
        });
    }

    private void handleOnClickEvent() {
        try {
            String fullCommand = gui.getCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(taskList, gui, storage);
        } catch (DukeException e) {
            gui.showResponse(e.getMessage());
        }
    }

    public static void main(String[] args) {
    }
}
