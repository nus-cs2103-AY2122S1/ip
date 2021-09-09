package ui;

import dialog.DialogException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Page for selecting a save file to navigate to and instantiate Alice
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.02
 * @since 0.01
 */
public class StartPage extends AnchorPane {
    @FXML
    private Button enterButton;
    @FXML
    private Button clearButton;
    @FXML
    private TextField inputField = new TextField();
    @FXML
    private ScrollPane saveFileScrollPane = new ScrollPane();
    @FXML
    private AnchorPane paneReference = new AnchorPane();
    @FXML
    private ListView<String> listView = new ListView<>();
    @FXML
    private Label output;

    private ArrayList<File> files;

    public StartPage() {
        fetchSaveFiles();
    }

    @FXML
    private void clearInputField() {
        inputField.setText("");
    }

    public void fetchSaveFiles() {
        files = new ArrayList<>(Arrays.asList(Storage.getFilesFromDirectory(
                Storage.DIRECTORY_PATH + Storage.DATA_PATH)));
        ObservableList<String> items = FXCollections.observableArrayList();
        for (File file : files) {
            if (file.isFile() && !file.isHidden()) {
                String fullFileName = file.getName();
                String fileName = fullFileName.substring(0, fullFileName.indexOf("."));
//                button.setOnAction(event -> {
//                    inputField.setText(button.getText());
//                });
                items.add(fileName);
            }
        }
        listView.setItems(items);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                inputField.setText(newValue);
            }
        });
    }

    @FXML
    private void handleFileNameInput() {
        if (inputField.getText().isBlank() || inputField.getText().isEmpty()) {
            return;
        }
        boolean containsFile = files.stream().anyMatch(
                file -> file.getName().equals(inputField.getText() + ".txt"));

        if (!containsFile) {
            AlertBox.display("Are you sure you want to create new file '" + inputField.getText() + "'",
                    e -> showChatPage());
        } else {
            showChatPage();
        }
    }

    private void showChatPage() {
        try {
            Stage stage = (Stage) paneReference.getScene().getWindow();
            FXMLLoader fxmlLoaderChatPage = new FXMLLoader(StartPage.class.getResource("/view/ChatPage.fxml"));
            AnchorPane ap = fxmlLoaderChatPage.load();
            Scene scene = new Scene(ap);
            fxmlLoaderChatPage.<ChatPage>getController().setAliceByFilename(inputField.getText());
            fxmlLoaderChatPage.<ChatPage>getController().setFileName(inputField.getText());
            fxmlLoaderChatPage.<ChatPage>getController().printWelcomeText();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
