package chad;

import java.io.IOException;

import chad.command.ChadInvalidCommandException;
import chad.command.Command;
import chad.command.CommandParser;
import chad.storage.StorageHandler;
import chad.task.TaskHandler;
import chad.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents the Chad chatbot.
 *
 * @author Jay Aljelo Saez Ting
 */
public class ChadChatBot extends Application {

    private static final String APP_WINDOW_TITLE = "ChadBot";

    private Ui ui;
    private CommandParser commandParser;
    private StorageHandler storageHandler;
    private TaskHandler taskHandler;
    private boolean hasErrorOnSave;

    /**
     * Reads the input, and if it is a valid command, executes it.
     *
     * @param input The input text.
     */
    public void readInput(String input) {
        try {
            Command command = commandParser.getCommandInstance(input);
            command.execute(taskHandler, ui);
            if (hasErrorOnSave) {
                ui.displayUnexpectedErrorMessage();
                Platform.exit();
            }
            if (command.mustExit()) {
                Platform.exit();
            }
        } catch (ChadInvalidCommandException e) {
            ui.displayInvalidCommandErrorMessage(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChadChatBot.class.getResource("/view/Ui.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            initialiseStage(stage, scene);
            initialiseComponents(fxmlLoader);
            stage.show();
            ui.displayGreeting();
        } catch (IOException e) {
            e.printStackTrace();
            if (ui != null) {
                ui.displayUnexpectedErrorMessage();
            }
            Platform.exit();
        }
    }

    private void initialiseComponents(FXMLLoader fxmlLoader) throws IOException {
        ui = fxmlLoader.getController();
        ui.setChatBot(this);
        commandParser = new CommandParser();
        storageHandler = StorageHandler.getInstance();
        taskHandler = new TaskHandler(storageHandler.loadTasks());
        taskHandler.addTasksListUpdateObserver(tasks -> {
            try {
                storageHandler.saveTasks(tasks);
            } catch (IOException e) {
                hasErrorOnSave = true;
            }
        });
        hasErrorOnSave = false;
    }

    private void initialiseStage(Stage stage, Scene scene) {
        stage.setTitle(APP_WINDOW_TITLE);
        Image appIcon = new Image("/images/yeschad_toleft.png");
        stage.getIcons().add(appIcon);
        stage.setScene(scene);
    }
}
