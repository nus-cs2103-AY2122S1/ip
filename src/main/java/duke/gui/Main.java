package duke.gui;


import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main Stage of the application
 */
// Solution adapted from https://se-education.org/guides/tutorials/javaFx.html
public class Main extends Application {
    private final Duke duke = new Duke();
    private final FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

    /**
     * Method to start the application
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) {
            setPrimaryScene(primaryStage);
            setupMainWindow();
            primaryStage.setOnHiding( event ->  setupCloseEvent());
            primaryStage.show();
    }

    /**
     * Setting up primary stage with anchorpane scene
     *
     * @param primaryStage
     */
    private void setPrimaryScene(Stage primaryStage) {
        try {
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Setting up Main window controller
     */
    private void setupMainWindow() {
        fxmlLoader.<MainWindow>getController().setDuke(duke);
        fxmlLoader.<MainWindow>getController().setScrollPane();
    }

    /**
     * Setting up a save command whenever stage closes
     */
    private void setupCloseEvent() {
        System.out.println("Closing Stage");
        duke.saveOnClosed();
    }

}
