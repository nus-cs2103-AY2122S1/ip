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
            System.out.println(e);
            ui.showLoadingError();
            storage.createNewFile();
            taskList = new TaskList();
        }
    }

    /**
     * Runs Duke chatbot and allows it to continuously receive inputs from the user.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Main function of the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        //new Duke("data/tasks.txt").run();
        Application.launch(Main.class, args);
    }
}
