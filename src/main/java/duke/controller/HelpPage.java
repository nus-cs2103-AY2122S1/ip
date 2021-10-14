package duke.controller;

import java.io.IOException;

import duke.Main;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for Help Page.
 */
public class HelpPage extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button exitButton;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<Command, String> command;

    @FXML
    private TableColumn<Command, String> usage;

    @FXML
    private TableColumn<Command, String> format;

    private final Stage stage;
    private final Scene prev;

    /**
     * Constructs a HelpPage object
     *
     * @param stage stage
     * @param prev previous scene
     */
    public HelpPage(Stage stage, Scene prev) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/HelpPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.stage = stage;
        this.prev = prev;
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Builds the help page
     */
    public void start() {
        command.setCellValueFactory(new PropertyValueFactory<>("cmd"));
        usage.setCellValueFactory(new PropertyValueFactory<>("usage"));
        format.setCellValueFactory(new PropertyValueFactory<>("format"));

        ObservableList<Command> data = FXCollections.observableArrayList(
                new ByeCommand(), new HelpCommand(), new ListCommand(),
                new DoneCommand(), new DeleteCommand(), new TodoCommand(),
                new DeadlineCommand(), new EventCommand(), new FindCommand()
        );
        tableView.setItems(data);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * Returns to previous scene
     */
    @FXML
    public void exitHelpPage() {
        stage.setScene(prev);
        stage.show();
    }
}
