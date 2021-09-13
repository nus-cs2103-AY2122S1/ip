package duke;

import duke.controller.MainWindow;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Duke dukeChatbot;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            dukeChatbot = new Duke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(dukeChatbot);
            stage.setTitle("Your friendly Duke Chatbot");
            stage.setResizable(false);
            stage.show();
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }
}
