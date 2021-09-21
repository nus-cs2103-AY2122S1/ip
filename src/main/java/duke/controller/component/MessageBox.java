package duke.controller.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * MessageBox component.
 */
public class MessageBox extends Label {
    private static final int PADDING = 10;
    private static final int PREF_WIDTH = 200;
    private static final int MIN_HEIGHT = 65;
    private static final int FONT_SIZE = 15;
    private static final boolean IS_WRAP_TEXT = true;

    /**
     * Constructs a MessageBox object.
     *
     * @param content Content that will be shown in the dialog.
     * @param layoutX MessageBox layoutX.
     * @param layoutY MessageBox layoutY.
     */
    public MessageBox(String content, double layoutX, double layoutY) {
        super();
        init(layoutX, layoutY);
        setText(content);
    }

    /**
     * Constructs a MessageBox object.
     *
     * @param text Content that will be shown in the dialog.
     * @param color Content text color.
     * @param layoutX MessageBox layoutX.
     * @param layoutY MessageBox layoutY.
     * @param height MessageBox height.
     */
    public MessageBox(String text, Color color, double layoutX, double layoutY, double height) {
        super();
        init(layoutX, layoutY);
        setMinHeight(height);
        setText(text);
        setTextFill(color);
    }

    private void init(double layoutX, double layoutY) {
        setLayoutX(layoutX);
        setLayoutY(layoutY);
        setPadding(new Insets(PADDING));
        setBackground(
                new Background(
                        new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        setPrefWidth(PREF_WIDTH);
        setMinHeight(MIN_HEIGHT);
        setFont(new Font(FONT_SIZE));
        setWrapText(IS_WRAP_TEXT);
    }
}
