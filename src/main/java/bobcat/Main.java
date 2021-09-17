package bobcat;

import java.io.IOException;

import bobcat.exception.BobCatException;
import bobcat.executor.ExecutionUnit;
import bobcat.view.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            ExecutionUnit executor = new ExecutionUnit("memory/taskList.txt");

            stage.setScene(scene);
            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setExecutor(executor);
            mainWindow.setStage(stage);
            stage.setTitle("BobCat");
            stage.show();

            mainWindow.respondBobCat(new String[]{"Hello! I'm BobCat!", "Trying to remember what happened..."});
            try {
                executor.initStorage();
            } catch (IOException e) {
                mainWindow.respondBobCat(new String[]{"Memory file not found! Starting from blank state..."});
            } catch (BobCatException e) {
                mainWindow.respondBobCat(new String[]{"Memory may have been corrupted! Starting from blank state..."});
                executor.clearStorage();
            } finally {
                mainWindow.respondBobCat(new String[] {"Initialisation done!", "What can I do for you?"});
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
