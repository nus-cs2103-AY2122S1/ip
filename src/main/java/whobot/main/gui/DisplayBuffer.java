package whobot.main.gui;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import whobot.main.Gui;
import whobot.main.UI;

public class DisplayBuffer {

    private static final ArrayList<String> buffer = new ArrayList<>();

    private static VBox parent = null;

    private static TextField userInput;

    private static Button sendButton;

    private static int charCount = 0;

    /***
     * Adds given text to the print buffer
     *
     * @param texts Variable Number of strings to add to buffer
     */
    public static void addToBuffer(String... texts) {
        Arrays.stream(texts).sequential().forEach(text -> {
            text = text.replaceAll("[\t]", " ");
            text = text.replace(UI.COLOR_BLUE, "");
            text = text.replace(UI.COLOR_PURPLE, "");
            text = text.replace(UI.COLOR_RESET, "");
            text = text.replace(UI.COLOR_CYAN, "");
            text = text.replace(UI.COLOR_RED, "");
            if (!text.isBlank()) {
                charCount += text.length();
                buffer.addAll(Arrays.asList(text.split("\n")));
            }
        });
    }

    /***
     * Sets the Parent Container to Display Dialog
     *
     * @param parent Scroll Pane Parent
     */
    public static void setParent(VBox parent) {
        DisplayBuffer.parent = parent;
    }

    /***
     * Sets the user input to the input box to block
     *
     * @param userInput Input Text Field
     */
    public static void setUserInput(TextField userInput) {
        DisplayBuffer.userInput = userInput;
    }

    /***
     * Sets the button to the Send Button to block
     *
     * @param sendButton Send Button
     */
    public static void setSendButton(Button sendButton) {
        DisplayBuffer.sendButton = sendButton;
    }

    /***
     * Displays Lines in Buffer
     */
    public static void printBuffer() {
        long delay = charCount > Gui.getShortTextLimit()
                ? Gui.getLongTextDelay()
                : Gui.getShortTextDelay();
        Thread printThread = new Thread(() -> {
            if (userInput != null && sendButton != null) {
                userInput.setDisable(true);
                sendButton.setDisable(true);
                int i = 0;
                if (!buffer.isEmpty()) {
                    int finalI = i;
                    Platform.runLater(() -> {
                        final Node node = BotDialogBox.getDialog(buffer.get(finalI), false, delay);
                        FadeTransition transition = new FadeTransition(Duration.millis(300), node);
                        transition.setFromValue(0);
                        transition.setToValue(1);
                        parent.getChildren().add(node);
                        transition.play();
                    });
                    try {
                        Thread.sleep(delay * buffer.get(i).length());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

                if (buffer.size() > 1) {
                    for (; i < buffer.size(); i++) {
                        int finalI1 = i;
                        Platform.runLater((() -> {
                            final Node node = BotDialogBox.getDialog(buffer.get(finalI1), true, delay);
                            FadeTransition transition = new FadeTransition(Duration.millis(300), node);
                            transition.setFromValue(0);
                            transition.setToValue(1);
                            parent.getChildren().add(node);
                            transition.play();
                            if (finalI1 == buffer.size() - 1) {
                                buffer.removeIf(c -> true);
                            }
                        }));
                        try {
                            Thread.sleep(delay * buffer.get(i).length());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (buffer.size() == 0) {
                            userInput.setDisable(false);
                            sendButton.setDisable(false);
                        }
                    }
                } else {
                    buffer.removeIf(c -> true);
                    userInput.setDisable(false);
                    sendButton.setDisable(false);
                }
            }
        });
        printThread.start();
    }
}
