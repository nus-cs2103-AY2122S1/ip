package main.java;

import main.java.tasklist.TaskList;
import main.java.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The Duke program implements an application that helps to track tasks that the user requires to be tracked.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class Duke extends Application {

    /**
     * Initialises app.
     * @param args empty args.
     */
    public static void main(String[] args) {
        Ui messages = new Ui();
        TaskList engine = new TaskList();

        messages.welcomeMessage();
        engine.runProgram();
        messages.goodbyeMessage();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
