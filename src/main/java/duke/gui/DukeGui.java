package duke.gui;

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

public abstract class DukeGui extends Application {
    protected ScrollPane scrollPane;
    protected VBox dialogContainer;
    protected TextField userInput;
    protected Button sendButton;
    protected Scene scene;
    protected Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    protected Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Creates the Duke GUI from a stage.
     * @param stage The stage on which to render the scene
     */
    protected void setUpGui(Stage stage) {
        setUpGuiComponents(stage);
        adjustComponentSizes();
    }

    /**
     * Creates components of the GUI.
     * @param stage The stage on which to render the scene.
     */
    private void setUpGuiComponents(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle("Duke ChatBot");
        stage.setResizable(false);
        stage.setMinHeight(700.0);
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(600.0, 700.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets sizes and scroll settings for components.
     */
    private void adjustComponentSizes() {
        scrollPane.setPrefSize(585, 635);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(525.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
}
