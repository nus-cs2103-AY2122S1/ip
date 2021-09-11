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
    private ContactsList contacts;
    private Ui ui = new Ui(this);

    /**
     * Duke constructor that takes in the file path for the save file.
     *
     */
    public Duke() {

    }

    private void initialiseDuke(String filePath, String contactsFilePath) {
        try {
            this.storage = new Storage(filePath, contactsFilePath);
            this.tasks = new TaskList(storage.produceReadableString(), this.storage, this.ui);
            this.contacts = new ContactsList(storage.produceReadableContactsString(), this.storage, this.ui);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("Generating empty task list!");
            this.tasks = new TaskList(this.ui);
        }
    }

    /**
     * Runs the program.
     *
     */
    public void runCommand(String userInput) {
        try {
            Command c = Parser.parseCommand(userInput);
            assert this.tasks != null;
            assert this.storage != null;
            assert this.contacts != null;
            assert this.ui != null;
            this.ui.addDialog(userInput, false);
            c.execute(tasks, storage, ui, contacts);
        } catch (DukeException e) {
            ui.addDialog(e.getMessage(), true);
        }
    }

    @Override
    public void start(Stage stage) {
        initialiseDuke("saves/saves.txt", "saves/contacts.txt");
        Scene scene = ui.initialiseScene(stage);

        stage.setScene(scene);
        stage.show();

    }

}
