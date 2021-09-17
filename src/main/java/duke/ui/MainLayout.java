package duke.ui;

import javafx.scene.layout.AnchorPane;

public class MainLayout extends AnchorPane {

    /**
     * Creates a new AnchorPane and sets its height and width to the given values.
     *
     * @param height the given height
     * @param width the given width
     */
    public MainLayout(double height, double width) {
        super();
        this.setPrefSize(width, height);
    }

    public void setAnchors(DukeScrollPane scrollPane, SendButton sendButton, UserInput userInput) {
        this.getChildren().addAll(scrollPane, userInput, sendButton);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
}
