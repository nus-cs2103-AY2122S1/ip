package duke.io;

import javafx.scene.control.TextField;

public class GuiUserInputHandler implements UserInputHandler {
    private final TextField userInputTextField;

    public GuiUserInputHandler(TextField userInputTextField) {
        this.userInputTextField = userInputTextField;
    }

    @Override
    public String readInput() {
        return userInputTextField.getText();
    }
}
