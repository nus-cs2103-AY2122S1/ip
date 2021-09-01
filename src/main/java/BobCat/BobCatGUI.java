package BobCat;

import BobCat.exception.BobCatException;
import BobCat.executor.ExecutionUnit;
import BobCat.view.GraphicalUi;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class BobCatGUI extends Application {
    private ScrollPane scrollPane;
    private TextField userInput;

    private GraphicalUi gui;
    private ExecutionUnit executor;

    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image bobcat = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaBobCat.png")));

    @Override
    public void start(Stage stage) throws Exception {
        scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        gui = new GraphicalUi(dialogContainer, user, bobcat); // How to display properly in GUI
        executor = new ExecutionUnit("memory/taskList.txt");

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Scene scene = new Scene(mainLayout);

        gui.respondBobCat(new String[]{"Hello! I'm BobCat!", "Trying to remember what happened..."});
        try {
            executor.initStorage();
        } catch (IOException e) {
            gui.respondBobCat(new String[]{"Memory file not found! Starting from blank state..."});
        } catch (BobCatException e) {
            gui.respondBobCat(new String[]{"Memory may have been corrupted! Starting from blank state..."});
            executor.clearStorage();
        } finally {
            gui.respondBobCat(new String[] {"Initialisation done!", "What can I do for you?"});
        }

        sendButton.setOnMouseClicked((event) -> handlerUserInput());
        userInput.setOnAction((event) -> handlerUserInput());

        stage.setScene(scene);
        stage.show();
    }

    private void handlerUserInput() {
        gui.respondUser(new String[]{userInput.getText()});
        try {
            gui.respondBobCat(executor.executeCommand(userInput.getText()));
        } catch (BobCatException e) {
            gui.respondError(e);
        } catch (IOException e) {
            gui.respondError(new BobCatException(e.getMessage()));
        }
        userInput.clear();
    }
}
