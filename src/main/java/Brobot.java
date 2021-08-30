import brobot.Storage;
import brobot.UI;
import brobot.exception.BroException;
import brobot.parser.BroParser;
import brobot.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents the main Duke Program, a task manager.
 */
public class Brobot extends Application {
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
    /**
     * Main java program
     * @param args Arguments for main program
     */
    public static void main(String[] args) {

        Storage storage = new Storage("./data/list1.txt");
        TaskList list = storage.readList();
        BroParser parser = new BroParser(list, storage);

        UI.printGreeting();
        String input = UI.getUserInput();

        while (!input.equals("bye")) {
            try {
                parser.parse(input);
            } catch (BroException e) {
                UI.printError(e);
            } finally {
                input = UI.getUserInput();
            }
        }
        UI.printBye();
    }
}
