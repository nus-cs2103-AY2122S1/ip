package duke;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Consumer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import duke.task.Task;

/**
 * Handles Duke's User Interface.
 */
public class Ui extends Application {

    private static Consumer<String> inputHandler;
    private static MainWindow mainWindow;

    /**
     * Get Duke to send a welcome message.
     */
    private static void welcomeMessage() {
        print("Greetings, my subject.");
    }

    /**
     * Get Duke to send a given String.
     *
     * @param text String to send
     */
    public static void print(String text) {
        mainWindow.print(text);
    }

    /**
     * Get Duke to send a given collection of tasks.
     *
     * @param tasks given collection of tasks
     */
    public static void printTasks(Collection<Task> tasks) {
        int i = 0;
        StringBuilder listString = new StringBuilder();

        for (Task t : tasks) {
            listString.append(String.format("%d. %s\n", i + 1, t));
            i++;
        }

        Ui.print(listString.toString());
    }

    public static void setInputHandler(Consumer<String> handler) {
        inputHandler = handler;
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            //There should only be a single instance of mainWindow
            assert(mainWindow == null);

            mainWindow = fxmlLoader.getController();
            mainWindow.setInputHandler(inputHandler);

            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Ui.welcomeMessage();
    }
}
