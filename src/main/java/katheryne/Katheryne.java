package katheryne;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import katheryne.command.Command;

@JsonTypeInfo(use = NAME, include = PROPERTY)

/**
 * Chat bot katheryne.Katheryne, used for simple todo lists
 */
public class Katheryne extends Application {

    public Katheryne() {
    }

    public static void main(String[] args) {
        // initialise variables
        final String pathName = "tasks.json";
        Storage storage = new Storage();
        Ui ui = new Ui();
        TaskList lst = new TaskList();

        // initialise Katheryne
        try {
            storage.loadTasks(lst, pathName);
        } catch (KatheryneException e) {
            ui.showErrorMessage(e);
        }
        ui.greet(lst);

        while (ui.getIsRunning()) {
            try {
                String userInput = ui.readCommand();
                Command c = Parser.parse(userInput);
                c.execute(lst, ui, storage);
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            } catch (KatheryneException e) {
                System.out.println(e.getMessage());
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
}
