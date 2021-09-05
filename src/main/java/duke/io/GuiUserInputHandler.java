package duke.io;

import static duke.gui.DialogBox.generateUserDialogBox;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * This class is responsible for handling user input from the GUI.
 *
 * @author kevin9foong
 */
public class GuiUserInputHandler implements UserInputHandler {
    private final TextField userInputTextField;
    private final Pane dialogContainer;
    private final Image userAvatar;

    /**
     * Constructs an instance of <code>GuiUserInputHandler</code> which extracts user input from given
     * <code>userInputTextField</code> and handles input.
     * @param userInputTextField <code>TextField</code> which contains user input to extract.
     * @param dialogContainer dialogContainer to display dialog boxes within.
     * @param userAvatar image used for user's avatar.
     */
    public GuiUserInputHandler(TextField userInputTextField, Pane dialogContainer, Image userAvatar) {
        this.userInputTextField = userInputTextField;
        this.dialogContainer = dialogContainer;
        this.userAvatar = userAvatar;
    }

    /**
     * Displays user dialog box into the provided dialogContainer and extracts user input to be handled by chat bot.
     * @return user input to be handled by chat bot.
     */
    @Override
    public String handleInput() {
        String userInput = userInputTextField.getText();
        dialogContainer.getChildren().addAll(
                generateUserDialogBox(userInput, this.userAvatar));
        return userInput;
    }
}
