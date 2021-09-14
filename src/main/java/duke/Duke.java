package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a runner for the Duke program.
 */
public class Duke extends Application {

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private UserInterface ui;
    private DateTimeFormatter dtformatter;
    private Storage storage;
    private TaskList taskList;

    /**
     * Class constructor.
     */
    public Duke() {
        ui = new UserInterface();
        dtformatter = DateTimeFormatter.ISO_DATE;
        String home = System.getProperty("user.home");
        Path dukePath = Paths.get(home, "Documents", "duke", "data.csv");
        storage = new Storage(dukePath);
        taskList = storage.load(ui);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label helloWorld = new Label("Hello World!");
        helloWorld.setFont(Font.font("Ariel", 50));
        Scene scene = new Scene(helloWorld);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Runs this Duke program.
     */
    public void run() {
        ui.displayGreeting();
        Command command;
        do {
            command = Parser.parse(ui.getResponse());
            command.execute(taskList, ui);
        } while(command.shouldExecuteNext());
        storage.save(ui, taskList);
    }
}
