package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import duke.command.Command;

/**
 * Duke is the main class that handles the overall execution of the program.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui(this);

    /**
     * Duke constructor that takes in the file path for the save file.
     *
     */
    public Duke() {

    }

    private void initialiseDuke(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.produceReadableString(), this.storage, this.ui);
        } catch (DukeException e) {
            System.out.println("Save file could not be loaded!");
            System.out.println("Generating empty task list!");
            this.tasks = new TaskList(this.ui);
        }
    }

    /**
     * This method runs the program.
     *
     */
    public void runCommand(String userInput) {
        try {
            Command c = Parser.parseCommand(userInput);
            c.execute(tasks, storage, ui);
        } catch (DukeException e) {
            ui.addDialog(e.getMessage(), true);
        }
    }

    @Override
    public void start(Stage stage) {
        initialiseDuke("saves/saves.txt");
        Scene scene = ui.initialiseScene(stage);

        stage.setScene(scene);
        stage.show();

    }

/*
    public static void main(String[] args) {
        //new Duke("saves/saves.txt").run();
        new Duke("saves/saves.txt").run();
    }

 */

}
