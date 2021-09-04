package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import java.io.File;

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

public class Duke {
    private final Parser PARSER;
    private final Ui UI;
    private static final String STORAGE_FILEPATH = "data" + File.separatorChar + "duke-storage.txt";

    public Duke() {
        Storage dukeStore = new Storage(STORAGE_FILEPATH);
        TaskList taskList = TaskList.of(dukeStore);
        PARSER = Parser.initialize(taskList);
        UI = new Ui();
    }

//    public void run() {
//        while (UI.isRunning()) {
//            if (!UI.isPendingReply()) {
//                continue;
//            }
//
//            String userInput = UI.readCommand();
//            if (userInput.equals("bye")) {
//                UI.close();
//            }
//
//            try {
//                String reply = PARSER.parseCommand(userInput);
//                Ui.printFormatted(reply);
//            } catch (DukeException e) {
//                Ui.printFormatted(e.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public String getResponse(String userInput) {
        String reply = "defaultReply";
        try {
            reply = PARSER.parseCommand(userInput);
        } catch (DukeException e) {
            reply = e.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }
}

