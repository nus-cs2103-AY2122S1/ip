package duke.controller;

import duke.Main;
import duke.command.*;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for Help Page.
 */
public class HelpPage extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button exitButton;

    @FXML
    private Label label;

    private Stage stage;
    private Scene prev;

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

    public void start() {
        label.setText("Here's some help!");
        TableView tableView = new TableView();
        TableColumn<Command, String> command = new TableColumn<>("Command");
        TableColumn<Command, String> function = new TableColumn<>("Function");
        TableColumn<Command, String> format = new TableColumn<>("Format");

        command.setCellValueFactory(
                new PropertyValueFactory<>("cmd")
        );

        function.setCellValueFactory(
                new PropertyValueFactory<>("usage")
        );

        format.setCellValueFactory(
                new PropertyValueFactory<>("format")
        );

        tableView.getColumns().add(command);
        tableView.getColumns().add(function);
        tableView.getColumns().add(format);

        ObservableList<Command> data = FXCollections.observableArrayList(
                new ByeCommand(), new HelpCommand(), new ListCommand(),
                new DoneCommand(), new DeleteCommand(), new TodoCommand(),
                new DeadlineCommand(), new EventCommand(), new FindCommand()
        );

        tableView.setItems(data);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label,tableView, exitButton);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void exitHelpPage() {
        stage.setScene(prev);
        stage.show();
    }
}
