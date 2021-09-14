package duke.controller;

import duke.Main;
import duke.command.Command;
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
                new PropertyValueFactory<>("fn")
        );

        format.setCellValueFactory(
                new PropertyValueFactory<>("format")
        );

        tableView.getColumns().add(command);
        tableView.getColumns().add(function);
        tableView.getColumns().add(format);

        ObservableList<Command> data = FXCollections.observableArrayList(
                new Command("bye", "exit app", "bye"),
                new Command("list", "list all functions", "list"),
                new Command("todo", "add tasks without any date/time attached",
                        "todo {task name}"),
                new Command("deadline", "add tasks that need to be done before a specific date & time\n",
                        "deadline {task name} /by {yyyy-MM-dd HH:mm}"),
                new Command("event", "add tasks that need that starts at a specific time\n",
                        "event {task name} /by {yyyy-MM-dd HH:mm}"),
                new Command("done", "marks specified task as done\n",
                        "done {task number}"),
                new Command("delete", "deletes specified task\n",
                        "delete {task number}"),
                new Command("find", "find a task by searching for a keyword\n",
                        "find {keyword}")
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
