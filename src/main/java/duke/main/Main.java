package duke.main;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Find;
import duke.commands.Task;
import duke.commands.Todo;
import duke.controllers.MainWindow;
import duke.data.DukeException;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

/**
 * Encapsulates the entire Duke program.
 * duke.main.Main class contains the storage, ui, tasklist, parser and finder objects,
 * to help run the program.
 *
 * @author: Jason Ng
 * @version: Duke Level-9
 */
public class Main extends Application {
    /** Storage component for Duke */
    private Storage storage;
    /** UI component for Duke */
    private Ui ui;
    /** TaskList component for Duke */
    private TaskList taskList;
    /** Parser component for Duke */
    private Parser parser;
    /** Find component for Duke */
    private Find finder;

    /**
     * Constructor for a duke.main.Main() class.
     */
    public Main() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.taskList = new TaskList(storage.Load());
        this.parser = new Parser();
        this.finder = new Find();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(new Duke());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

