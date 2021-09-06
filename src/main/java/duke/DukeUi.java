package duke;

import java.io.IOException;

import duke.command.DukeCommand;
import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
            DukeCommand command = DukeCommandParser.parseCommand(input);
            response = command.execute(Duke.getTList());
            if (command.isExit()) {
                Platform.exit();
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
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
