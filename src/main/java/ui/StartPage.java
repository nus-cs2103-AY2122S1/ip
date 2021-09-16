package ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import storage.Storage;
import ui.components.AlertBox;

/**
 * Page for selecting a save file to navigate to and instantiate Alice.
 * Start page will switch to chat page once user has chosen a save file.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.02
 */
public class StartPage extends AnchorPane {
    @FXML
    private Button enterButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField inputField = new TextField();
    @FXML
    private ScrollPane saveFileScrollPane = new ScrollPane();
    @FXML
    private AnchorPane paneReference = new AnchorPane();
    @FXML
    private ListView<String> listView = new ListView<>();

    private ArrayList<File> files;

    /**
     * Constructor of StartPage.
     * fetch save files and populate the list view.
     */
    public StartPage() {
        fetchSaveFiles();
    }

    @FXML
    private void clearInputField() {
        inputField.setText("");
    }

    /**
     * populate the listview of this start page with file names
     */
    public void fetchSaveFiles() {
        ObservableList<String> items = FXCollections.observableArrayList();
        addFileNamesToItems(items);
        setUpListViewWithItems(items);
    }

    private void addFileNamesToItems(ObservableList<String> items) {
        files = new ArrayList<>(Arrays.asList(Storage.getFilesFromDirectory(
            Storage.DIRECTORY_PATH + Storage.DATA_PATH)));
        for (File file : files) {
            if (file.isFile() && !file.isHidden()) {
                String fullFileName = file.getName();
                String fileName = fullFileName.substring(0, fullFileName.indexOf("."));
                items.add(fileName);
            }
        }
    }

    private void setUpListViewWithItems(ObservableList<String> items) {
        listView.setItems(items);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                inputField.setText(newValue);
            }
        });
    }

    /**
     * refresh the save files on the list view
     */
    public void refreshSaveFiles() {
        listView.refresh();
    }

    @FXML
    private void handleFileNameInput() {
        if (inputField.getText().isBlank()) {
            return;
        }
        // check if the input name is one of the file name
        boolean containsFile = listView.getItems().stream().anyMatch(
            fileName -> fileName.equals(inputField.getText()));

        if (!containsFile) {
            // prompt user to confirm creating new file first
            AlertBox.display("Are you sure you want to create new file '" + inputField.getText() + "'",
                e -> showChatPage());
        } else {
            // go to the chat page directly
            showChatPage();
        }
    }

    @FXML
    private void handleDeleteFile() {
        if (inputField.getText().isBlank()) {
            return;
        }
        // check if the input name is one of the file name
        boolean containsFile = listView.getItems().stream().anyMatch(
            fileName -> fileName.equals(inputField.getText()));

        if (containsFile) {
            // prompt user to confirm creating new file first
            AlertBox.display("Are you sure you want to delete your save file '" + inputField.getText() + "'",
                e -> {
                    deleteFile();
                    removeSaveFileFromListView();
                    refreshSaveFiles();
                });
        }
    }

    private void removeSaveFileFromListView() {
        listView.getItems().remove(inputField.getText());
    }

    private void deleteFile() {
        Storage.deleteFile(inputField.getText());
    }

    private void showChatPage() {
        Stage stage = (Stage) paneReference.getScene().getWindow();
        setUpStage(stage);
        stage.show();
    }

    private void setUpStage(Stage stage) {
        try {
            FXMLLoader fxmlLoaderChatPage = new FXMLLoader(StartPage.class.getResource("/view/ChatPage.fxml"));
            AnchorPane ap = fxmlLoaderChatPage.load();
            // set scene
            Scene scene = new Scene(ap);
            // implement alice before setting the scene to stage
            fxmlLoaderChatPage.<ChatPage>getController().setUpByFileName(inputField.getText());
            fxmlLoaderChatPage.<ChatPage>getController().putWelcomeText();
            // set and show scene to finalise
            stage.setTitle(inputField.getText());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
