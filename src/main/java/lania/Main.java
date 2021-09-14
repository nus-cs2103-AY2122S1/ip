package lania;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lania.task.TaskList;

//@@author nguyiyang-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications
/**
 * A GUI for Lania using FXML.
 */
public class Main extends Application {

    private Ui ui = new Ui();
    private Storage storage = new Storage("data/lania.txt");
    private TaskList tasks;
    private Parser parser = new Parser();
    private Log log = new Log();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Lania's task manager");
            tasks = storage.load();
            fxmlLoader.<MainWindow>getController().setLania(tasks, ui, storage, parser, log);
            fxmlLoader.<MainWindow>getController().startDialog();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author
