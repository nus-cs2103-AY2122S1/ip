package angrybot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Angry Bot using FXML.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class Main extends Application {
    private AngryBot angryBot;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        angryBot = new AngryBot();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setTitle("Angry Bot");
            primaryStage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAngryBot(angryBot);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
