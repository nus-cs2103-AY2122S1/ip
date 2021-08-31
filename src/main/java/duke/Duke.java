package duke;

import javafx.application.Platform;
import tasklist.TaskList;
import ui.MainWindow;
import ui.Ui;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * The Duke program implements an application that helps to track tasks that the user requires to be tracked.
 *
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class Duke {
    TaskList engineGui = new TaskList();
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


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return engineGui.readGuiInput(input);
    }
}
