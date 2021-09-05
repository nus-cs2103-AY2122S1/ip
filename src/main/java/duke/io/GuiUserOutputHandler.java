package duke.io;

import static duke.gui.DialogBox.generateAgentDialogBox;

import duke.messages.Message;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * This class is responsible for displaying chat bot agent's responses in a graphical form.
 *
 * @author kevin9foong
 */
public class GuiUserOutputHandler implements UserOutputHandler {
    private final Pane dialogContainer;
    private final Image agentAvatar;

    /**
     * Constructs a <code>GuiUserOutputHandler</code> which handles displaying
     * of dialog messages into the provided dialogContainer.
     * @param dialogContainer JavaFX <code>Pane</code> which contains the chat dialog boxes.
     * @param agentAvatar picture avatar for chat bot agent.
     */
    public GuiUserOutputHandler(Pane dialogContainer, Image agentAvatar) {
        this.dialogContainer = dialogContainer;
        this.agentAvatar = agentAvatar;
    }

    /**
     * Displays the message specified to the user in a graphical form.
     * @param msg message text content to be displayed.
     */
    @Override
    public void handleOutput(Message msg) {
        dialogContainer.getChildren().addAll(
                generateAgentDialogBox(msg.toString(), this.agentAvatar));
    }
}
