package duke.controller.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MessageBox extends Label {

    /**
     * Constructs a MessageBox object.
     *
     * @param text Content will be shown.
     * @param layoutX MessageBox layoutX.
     * @param layoutY MessageBox layoutY.
     */
    public MessageBox(String text, double layoutX, double layoutY) {
        super();
        init(layoutX, layoutY);
        setText(text);
    }

    /**
     * Constructs a MessageBox object.
     *
     * @param text Content will be shown.
     * @param layoutX MessageBox layoutX.
     * @param layoutY MessageBox layoutY.
     * @param height MessageBox height.
     */
    public MessageBox(String text, double layoutX, double layoutY, double height) {
        super();
        init(layoutX, layoutY);
        setMinHeight(height);
        setText(text);
    }

    private void init(double layoutX, double layoutY) {
        setLayoutX(layoutX);
        setLayoutY(layoutY);
        setPadding(new Insets(10, 10, 10, 10));
        setBackground(
            new Background(
                new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        setPrefWidth(200);
        setMinHeight(65);
        setFont(new Font(15));
        setWrapText(true);
    }
}
