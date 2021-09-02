package duke;

import duke.commands.Command;
import duke.gui.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The main class which runs the Duke chatbot.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("data/tasks.txt");
        try {
            taskList = storage.load();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            storage.createNewFile();
            taskList = new TaskList();
        }
    }


    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            String response = c.execute(taskList, ui, storage);
            if (c.isExit()) {
                System.exit(0);
            }
            return response;

        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return "";
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
