package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import task.TaskList;

/**
 * Main Duke class
 */
public class Duke extends Application {
//    private final DukeListener listener;
//
//    /**
//     * Constructor
//     * Instantiates a (saved) task.Task List and a duke.DukeListener for duke.Duke
//     */
//    private Duke() {
//        TaskList taskList = Storage.loadList();
//        DukeParser parser = new DukeParser(taskList);
//        listener = new DukeListener(parser);
//    }
//
//    /**
//     * Main function
//     *
//     * @param args No arguments need to be passed in
//     */
//    public static void main(String[] args) {
//        Duke duke = new Duke();
//        duke.begin();
//    }
//
//    /**
//     * Function that starts listening for the command
//     */
//    private void begin() {
//        Ui.intro();
//        listener.startListen();
//        Ui.goodBye();
//    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}
