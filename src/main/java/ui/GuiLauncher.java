package ui;

import jaden.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import middleware.Middleman;
import model.TaskList;
import ui.view.MainWindowController;

import java.io.IOException;

public class GuiLauncher extends Application  {

    public static void main(String[] args) {
        launch(args);
    }

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
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiLauncher.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            MainWindowController mainWindow = fxmlLoader.getController();

            TaskList taskList = new TaskList();
            JadenGui ui = new JadenGui(mainWindow);

            Middleman middleman = new Middleman(taskList, ui);
            Duke jaden = new Duke(middleman, ui);

            mainWindow.setDuke(jaden);

            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.show();
    }
}
