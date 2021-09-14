package duke;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * This class encapsulates Duke, an interactive task management chat-bot.
 *
 * @author Kleon Ang
 */
public class Duke extends Application {
    private static final String DATA_FILENAME = "duke.txt";
    private static Parser parser;
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Empty constructor for Duke to be launched by Launcher.
     */
    public Duke() {
    }

    /**
     * Gets response from Parser, then exit or rewrite data if needed.
     *
     * @param input User input.
     * @return The response from Duke.
     * @throws DukeException A Duke-specific exception that may occur when parsing user input.
     */
    public static String getResponse(String input) throws DukeException {
        String response = parser.parse(input);
        if (parser.needsToExit()) {
            return response;
        }
        if (parser.needsToRewrite()) {
            storage.rewriteData(tasks);
        }
        return response;
    }

    /**
     * Starts up the user interface with a given Stage.
     * @param stage A Stage for the user interface.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke™ – Your Task Management Assistant");
            fxmlLoader.<Ui>getController().printReply("Hello, I'm Duke!\nWhat can I do for you?");
            storage = new Storage(DATA_FILENAME);
            try {
                tasks = new TaskList(storage.load());
                fxmlLoader.<Ui>getController().showLoadingSuccess(DATA_FILENAME);
            } catch (DukeException e) {
                tasks = new TaskList();
                fxmlLoader.<Ui>getController().showLoadingError(DATA_FILENAME);
            }
            parser = new Parser(tasks);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
