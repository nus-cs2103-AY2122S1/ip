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
    private static final String STORAGE_FILEPATH = "data" + File.separatorChar + "duke-storage.txt";

    public Duke() {
        Storage dukeStore = new Storage(STORAGE_FILEPATH);
        TaskList taskList = TaskList.of(dukeStore);
        PARSER = Parser.initialize(taskList);
    }

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

