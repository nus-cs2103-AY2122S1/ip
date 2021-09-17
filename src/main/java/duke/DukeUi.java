package duke;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import duke.command.DukeCommand;
import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class DukeUi extends Application {
    private static String goodBye = "See ya next time! *quack* *quack* *quack*";

    public DukeUi() {
    }

    public static String getGoodBye() {
        return goodBye;
    }

    public static String getResponse(String input) {
        String response = "";
        try {
            DukeCommand command = DukeCommandParser.parseInput(input);
            response = command.execute(Duke.getTaskList());
            if (command.isExit()) {
                delayedExit();
            }
        } catch (DukeCommandException | DukeArgumentException e) {
            response = e.getMessage();
        }
        return "Duck says:\n" + response;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeUi.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Duck");
            primaryStage.getIcons().add(new Image(DukeUi.class.getResourceAsStream("/images/logo.jpg")));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author endriu_l/Jason C taken from stackoverflow.
     */
    private static void delayedExit() {
        TimerTask exit = new TimerTask() {
            public void run() {
                Platform.exit();
            }
        };
        new Timer().schedule(exit, 2000);
    }
}
