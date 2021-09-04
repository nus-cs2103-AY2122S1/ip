package duke.ui;

import javafx.scene.control.ScrollPane;

public class DukeScrollPane extends ScrollPane {

    private static final double SCROLLPANE_HEIGHT = 572.0;
    private static final double SCROLLPANE_WIDTH = 400.0;

    /**
     * Creates a new DukeScrollPane with the given DialogContainer.
     * Sets its height and width to the constants specified above.
     * And binds the dialogContainer to the DukeScrollPane.
     *
     * @param dialogContainer the given dialogContainer
     */
    public DukeScrollPane(DialogContainer dialogContainer) {
        super();

        this.setPrefSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.setContent(dialogContainer);
        this.vvalueProperty().bind(dialogContainer.heightProperty());
        this.setFitToWidth(true);
    }
}
