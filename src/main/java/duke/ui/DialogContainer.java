package duke.ui;

import javafx.scene.layout.VBox;

public class DialogContainer extends VBox {

    public DialogContainer() {
        super();
    }

    public void addMessage(MessageLabel messageLabel) {
        this.getChildren().add(messageLabel);
    }

    public void sendInputMessage(String msg) {
        addMessage(new InputMessageLabel(msg));
    }

    public void sendOutputMessage(String msg) {
        addMessage(new OutputMessageLabel(msg));
    }

    public void sendErrorMessage(String msg) {
        addMessage(new ErrorMessageLabel(msg));
    }
}
