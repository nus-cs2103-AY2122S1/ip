package duke;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke class used to run the duke.Duke chat-bot.
 * Contains methods that
 * (i)    initialises the Ui class and runs the chat-bot.
 */
public class Duke extends Application {
    /**
     * Initialises the chatBot and runs the Duke chatBot.
     * @param args arguments for main method.
     */
    public static void main(String[] args) {
        Ui chatBot = new Ui();
        chatBot.run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
