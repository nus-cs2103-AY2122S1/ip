package duke;

import duke.command.Command;
import duke.command.ExitCommand;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke {

    private Storage storage;
    private TaskList taskList;

    @SuppressWarnings("unused")
    public Duke() {
    }

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
    }

    public void run() {
        Ui ui = new Ui();
        ui.greet();

        boolean isExit = false;

        // duke.Duke continuously asks for the user's input until they type "bye"
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        new ExitCommand().execute(taskList, ui, storage);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("myTasks.txt");
        duke.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}