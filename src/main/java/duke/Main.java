package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private String filePath;

    private Duke duke;
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            this.fxmlLoader = fxmlLoader;
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            filePath = this.getFilePath().toAbsolutePath().toString();
            this.duke = new Duke(filePath);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            duke.run();
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Path getFilePath() {
        String userHome = System.getProperty("user.dir");
        return Paths.get(userHome, "taskLog.txt");
    }

    public void sendMessageToUser(String message) {
        this.fxmlLoader.<MainWindow>getController().sendMessageToUser(message);
    }
}
