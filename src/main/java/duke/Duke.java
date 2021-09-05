package duke;

import java.io.IOException;
import java.time.DateTimeException;

import duke.command.Command;
import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the Duke bot engine, handles everything.
 */
public class Duke extends Application {
    private final Ui ui;
    private final TaskManager taskManager;

    /**
     * Constructs the Duke Bot engine.
     */
    public Duke() {
        this.ui = new Ui();
        this.taskManager = new TaskManager();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            Duke duke = new Duke();
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            assert c != null : "c should not be a null command";
            return c.execute(taskManager);
        } catch (DukeException | IllegalArgumentException | DateTimeException e) {
            return e.getMessage();
        }
    }

    /**
     * Entrypoint of Duke bot engine.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.getUserInput();
            try {
                Command c = Parser.parse(userInput);
                ui.reply(c.execute(taskManager));
                isExit = c.isExit();
            } catch (DukeException | IllegalArgumentException | DateTimeException e) {
                ui.handleError(e);
            }
        }
    }

}
