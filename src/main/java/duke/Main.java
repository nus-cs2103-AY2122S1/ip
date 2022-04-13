package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** A GUI for Duke using FXML. */
public class Main extends Application {

    private Duke duke = new Duke("./data/task_list.txt");

    /**
     * Starts the JavaFX application.
     *
     * @param stage The JavaFX stage where the UI will be displayed on.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Lollipop Chatbot");

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            String firstMessage = duke.initializeTaskList();
            fxmlLoader.<MainWindow>getController().sendFirstMessage(firstMessage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
