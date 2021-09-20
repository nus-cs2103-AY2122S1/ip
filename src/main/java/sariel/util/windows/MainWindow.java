package sariel.util.windows;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sariel.util.commons.Messages;
import sariel.util.controller.Sariel;
import sariel.util.tasks.Task;





/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Text taskDisplay;
    @FXML
    private ListView<Task> listOfTasks;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField searchField;

    private Sariel sariel;


    private Image userImage = Messages.USER;
    private Image dukeImage = Messages.SARIEL;


    /**
     *Method called after the fields have been initialized in fxml.
     */
    @FXML
    public void initialize() {
        scrollPanePropertyInit();
        listSetSelectMultiple();
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(Messages.GREETINGS, dukeImage)
        );

    }


    private void scrollPanePropertyInit() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.hvalueProperty().bind(dialogContainer.widthProperty());
        //To bind the width of the scroll pane to the dialog container
        scrollPane.fitToWidthProperty().bind(dialogContainer.fillWidthProperty());
    }

    private void listSetSelectMultiple() {
        listOfTasks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * Initialises a Duke for this anchorPane.
     *
     * @param d The duke to be used.
     */
    public void setSariel(Sariel d) {
        assert d != null : "Duke that is set is null";
        sariel = d;
        sariel.setOut(listOfTasks);

        sariel.printList();
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sariel.getResponse(input);
        DialogBox inputDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox outputDialog = DialogBox.getDukeDialog(response, dukeImage);
        dialogContainer.getChildren().addAll(
                inputDialog,
                outputDialog
        );
        dialogContainer.setVgrow(inputDialog, Priority.ALWAYS);
        dialogContainer.setVgrow(outputDialog, Priority.ALWAYS);
        sariel.printList();
        userInput.clear();
    }

    /**
     * Handle the addition of tasks.
     *
     */
    @FXML
    private void addTask() {

        try {
            Stage stage = new Stage();
            TaskInputWindow.setStage(stage);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/TaskInputWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }


    }

    /**
     * Handle the removal of tasks through the GUI
     *
     */
    @FXML
    private void handleRemoveEvent() {
        sariel.removeHandler(this.listOfTasks.getSelectionModel().getSelectedItems());
        sariel.printList();
    }

    /**
     * Handle the marking of done tasks through the GUI
     *
     */
    @FXML
    private void handleDoneTasks() {
        ObservableList<Task> tasks = this.listOfTasks.getSelectionModel().getSelectedItems();
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).done();
        }
        sariel.printList();
    }

    @FXML
    private void handleSearchEntered() {
        //when there is nothing entered
        if (searchField.getText().equals("")) {
            handleClearButton();
            return;
        }
        sariel.filterDisplayList(task -> task.containsPattern(searchField.getText()));

    }

    @FXML
    private void handleClearButton() {
        sariel.filterDisplayList(task -> true);
    }



}


