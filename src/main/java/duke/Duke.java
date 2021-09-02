package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import duke.command.Command;
import duke.ui.ConsoleUi;

public class Duke extends Application {
    private ConsoleUi ui;
    private TaskList taskList;
    private Storage storage;

    public Duke() {
    }

    public Duke(Scanner in, String filePath) throws Exception {
        super();
        this.ui = new ConsoleUi(in);
        this.storage = new Storage(filePath);
        // TODO: throw error if unable to create file?
        this.taskList = new TaskList(storage.loadTasks());
    }

    /**
     * Runs the instance of {@link Duke}.
     */
    public void run() {
        ui.printGreeting();

        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                shouldExit = command.shouldExit();
            } catch (Exception e) {
                // TODO: custom Duke exceptions?
                ui.printMessage("Error: " + e.getMessage());
            }
        }

        ui.printGoodbye();
    }

    /**
     * Initializes an instance of the {@link Duke} class and runs the program.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String filePath = "duke.txt";
        Duke duke;

        try {
            duke = new Duke(inputScanner, filePath);
        } catch (Exception e) {
            // TODO: figure out static/non-static Ui class
            System.out.println("Unable to initialize data file");
            inputScanner.close();
            return;
        }

        duke.run();
        inputScanner.close();
    }

    /**
     * Starts the JavaFX GUI.
     */
    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}
