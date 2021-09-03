package ui;

import dialog.DialogException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import javafx.stage.Stage;
import storage.Storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class StartPage extends Page {

    @Override
    public Scene layout() {
        // This starting page was partly inspired by Marcus
        // Thank you Marcus :)
        Label welcome = new Label("Welcome to");
        Label logo = new Label("Alice");
        Label label = new Label("Please select your save file to start the app!");
        TextField inputField = new TextField();
        Label output = new Label("");
        Button button1 = new Button("Enter");
        Button button2 = new Button("Clear");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(600, 600);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(2);
        gridPane.setHgap(2);
        gridPane.setAlignment(Pos.CENTER);

        int columnIndex = 1;
        int rowIndex = 0;

        gridPane.add(welcome, columnIndex, rowIndex++);
        gridPane.add(logo, columnIndex, rowIndex++);

        ArrayList<File> files = new ArrayList<>(Arrays.asList(Storage.getFilesFromDirectory(
                Storage.DIRECTORY_PATH + Storage.DATA_PATH)));


        rowIndex++;
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).isFile() && !files.get(i).isHidden()) {
                String fullFileName = files.get(i).getName();
                Button button = new Button(fullFileName.substring(0, fullFileName.indexOf(".")));
                button.setOnAction(event -> {
                    inputField.setText(button.getText());
                });
                gridPane.add(button, columnIndex, rowIndex++);
            }
        }

        // Action event
        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                output.setText(inputField.getText());
            } else if (event.getCode() == KeyCode.ESCAPE) {
                output.setText("Output cleared.\nPlease enter a new command");
                inputField.setText("");
            }
        });

        EventHandler<ActionEvent> changeScreen = event -> {
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            String fullPath = this.getClass().getResource(".").getPath().toString();
            appStage.setTitle("Chat");
            try {
                appStage.setScene(new ChatPage(inputField.getText(), appStage).layout());
            } catch (IOException | DialogException e) {
                output.setText("Error encountered: " + e.getMessage());
            }
            appStage.show();
        };


        EventHandler<ActionEvent> clear = event -> {
            output.setText("Output cleared.\nPlease enter a new command");
            inputField.setText("");
        };

        // When button is pressed
        button1.setOnAction(changeScreen);
        button2.setOnAction(clear);

        // Arranging all the nodes in the grid
        columnIndex = 1;
        gridPane.add(label, columnIndex, rowIndex += 1);
        gridPane.add(inputField, columnIndex, rowIndex += 4);
        gridPane.add(button1, columnIndex, rowIndex += 3);
        gridPane.add(button2, columnIndex, rowIndex += 3);
        gridPane.add(output, columnIndex, rowIndex += 3);

        // Styling nodes
        button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; ");
        button1.setFont(Font.font("Calibri"));
        button2.setStyle("-fx-background-color: grey; -fx-text-fill: white; ");
        button2.setFont(Font.font("Calibri"));
        welcome.setStyle("-fx-font: normal 16px 'robota'; -fx-text-fill: rgba(255, 230, 130) ");
        label.setStyle("-fx-font: normal bold 20px 'calibri';" +
                " -fx-text-fill: rgba(255, 230, 130);");
        logo.setStyle("-fx-font: normal italic 72px 'verdana'; -fx-text-fill: rgba(255, 230, 130) ");
        gridPane.setStyle("-fx-background-color:\n" +
                "            linear-gradient(#5f6c99, #5f2c61),\n" +
                "            repeating-image-pattern(\"https://edencoding.com/resources/wp-content/uploads/2021/02/Stars_128.png\"),\n" +
                "            radial-gradient(center 50% 50%, radius 50%, #FFFFFF33, #00000033);");
        output.setStyle("-fx-font: normal 16px 'robota' ");

        return new Scene(gridPane);
    }

}
