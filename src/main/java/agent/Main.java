package agent;

import java.io.IOException;

import agent.exceptions.DukeException;
import agent.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Bridge class between UI and main chat bot logic.
 *
 * @author kevin9foong
 */
public class Main extends Application {

    /**
     * Starts the GUI for the chat bot.
     *
     * @param primaryStage top level JavaFX container for chat bot GUI.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            String mainWindowFxmlFilePath = "/view/MainWindow.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(mainWindowFxmlFilePath));
            AnchorPane mainLayoutPane = fxmlLoader.load();
            Scene scene = new Scene(mainLayoutPane);

            primaryStage.setScene(scene);
            configurePrimaryStage(primaryStage);

            MainWindow controller = fxmlLoader.getController();
            linkGuiToChatBotAgent(controller);

            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void configurePrimaryStage(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Butler Task Manager");
    }

    private void linkGuiToChatBotAgent(MainWindow mainWindow) {
        try {
            Agent agent = new Agent();
            mainWindow.linkChatBotAgent(agent);
        } catch (DukeException de) {
            mainWindow.displayAgentMessage(de.getMessage());
            mainWindow.disableUserInput();
        }
    }
}
