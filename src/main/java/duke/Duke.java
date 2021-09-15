package duke;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import static java.lang.Thread.sleep;
import static javafx.application.Platform.exit;


public class Duke{
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for a new Duke object.
     *
     * @param filePath File path for the storage file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = storage.loadTask();
        ui = new Ui(storage, taskList);
    }

    public Duke() {
        new Duke("data/duke.txt");
    }
    /**
     * Executes the Duke object.
     */
    public void run() {
        System.out.println(returnOutput("hehehe"));
        boolean isExit = false;
        ui.greet();
        taskList.printTask();
        while (!isExit) {
            String input = ui.readLine();
            ui.handleInput(input);
            ui.showLine();
            isExit = ui.handleExit();
        }
    }
    public String turnOn() {
        return ui.greet() + taskList.printTask();
    }
    public String returnOutput(String input) {
//        System.out.println(ui.handleInput(input));

        return ui.handleInput(input);
    }

    /**
     * Creates new Dukc object and runs it.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }


}