package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import task.TaskList;

import java.util.concurrent.TimeUnit;

/**
 * Main Duke class
 */
public class Duke {
    private final DukeListener listener;

    /**
     * Constructor
     * Instantiates a (saved) task.Task List and a duke.DukeListener for duke.Duke
     */
    protected Duke() {
        TaskList taskList = Storage.loadList();
        DukeParser parser = new DukeParser(taskList);
        listener = new DukeListener(parser);
    }

    /**
     * Main function
     *
     * @param args No arguments need to be passed in
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.begin();
    }

    /**
     * Function that starts listening for the command
     */
    private void begin() {
        Ui.intro();
        listener.startListen();
        Ui.goodBye();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
