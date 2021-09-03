package Duke;

import Duke.Tasks.Task;
import Duke.Tool.Parser;
import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui.DialogBox;
import Duke.Ui.Ui;

import java.io.IOException;
import java.lang.String;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * @author  Zhang Zhiyao
 * @version 1.0
 */

/**
 * This is the Main class that will contain the main method
 * to be executed at run-time.
 */
public class Duke {

    private Storage storage;
    private TaskList task;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpeg"));

    /**
     * The constructor of Duke
     * @param filePath
     */
    public Duke(String filePath)  {

        ui = new Ui();
        storage = new Storage(filePath);
        try {
            task = new TaskList(storage.readData());
        } catch (IOException e) {
            ui.showLoadingError();
            task = new TaskList();
        }
    }


    public Duke() {
        this("data/tasks.txt");
    }

    public String getResponse(String input) {
        Task comingCmdTask = Parser.parse(input, task);
        return comingCmdTask.execute(task, ui, storage);
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * The method of run
     */
//    public void run() {
//        ui.showLogo();
//        ui.showWelcome();
//        boolean isProcess = true;
//        while (isProcess) {
//            try {
//                String cmd = ui.getCommand();
//                Task comingCmdTask = Parser.parse(cmd, task);
//                comingCmdTask.execute(task, ui, storage);
//                isProcess = !comingCmdTask.isExit;
//            } catch (NullPointerException e) {
//                continue;
//            }
//
//        }
//        ui.exit();
//    }

//    @Override
//    public void start(Stage stage) {
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//    }

    /**
     * the main method
     * @param args
     */
//    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
//    }



}


