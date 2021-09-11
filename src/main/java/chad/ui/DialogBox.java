package chad.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Controller class for a single dialog box.
 * Adapted/reused from https://se-education.org/guides/tutorials/javaFxPart4.html written by Jeffry Lum.
 */
public class DialogBox extends HBox {

    private static final double DEFAULT_IMAGE_WIDTH = 99.0;
    private static final double MIN_WIDTH_TO_DISPLAY_IMAGE = 350.0;
    private static final double WIDTH_REDUCTION_FACTOR = 5.0;
    private static final double PSEUDO_HIDDEN_IMAGE_FIT_WIDTH = 0.1;

    /**
     * These constants are based off of the following math:
     *
     * Let
     * w be MIN_WIDTH_TO_DISPLAY_IMAGE,
     * d be DEFAULT_IMAGE_WIDTH,
     * r be WIDTH_REDUCTION_FACTOR,
     * f be the width reduction fraction, so f = 1 / r,
     * x be the dialogLayout width,
     * y be the displayPicture fitWidth.
     *
     * Suppose we want the following to be true:
     * When x = w, then y = d, and
     * when x = w * (1 - f), then y = 0.
     *
     * Then clearly, to find a linear relationship between x and y to achieve that, we can use the fact that
     * y - y_1 = (y_1 - y_0) * (x - x_1) / (x_1 - x_0)
     *
     * So,
     * y - d = (d - 0) * (x - w) / (w - w * (1 - f))
     *
     * which means that
     * y = (d / (w * f)) * x + d * (1 - 1 / f)
     *
     * or
     * y = (d * r / w) * x + d * (1 - r)
     */
    private static final double FACTOR_ENLARGED_DEFAULT_IMAGE_WIDTH = DEFAULT_IMAGE_WIDTH * WIDTH_REDUCTION_FACTOR;
    private static final double IMAGE_FIT_WIDTH_INTERCEPT = DEFAULT_IMAGE_WIDTH - FACTOR_ENLARGED_DEFAULT_IMAGE_WIDTH;
    private static final double ENLARGED_DEFAULT_MIN_WIDTH_GRADIENT =
            FACTOR_ENLARGED_DEFAULT_IMAGE_WIDTH / MIN_WIDTH_TO_DISPLAY_IMAGE;

    private static final String COMMAND_DIALOG_BACKGROUND_RADIUS_STYLE = "-fx-background-radius: 16 0 16 16;";
    private static final String COMMAND_DIALOG_BACKGROUND_COLOUR_STYLE = "-fx-background-color: #90EE90;";
    private static final String RESPONSE_DIALOG_BACKGROUND_RADIUS_STYLE = "-fx-background-radius: 0 16 16 16;";
    private static final String NORMAL_RESPONSE_DIALOG_BACKGROUND_COLOUR_STYLE = "-fx-background-color: #ADD8E6;";
    private static final String ERROR_RESPONSE_DIALOG_BACKGROUND_COLOUR_STYLE = "-fx-background-color: #FF7F7F;";

    @FXML
    private HBox dialogLayout;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) {
        initialiseFxml();
        initialiseDialogLayout();
        initialiseDialog(text);
        initialiseDisplayPicture(image);
    }

    private void initialiseFxml() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialiseDialogLayout() {
        dialogLayout.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double dialogLayoutWidth = newValue.doubleValue();
                double imageFitWidth = calculateAppropriateImageFitWidth(dialogLayoutWidth);
                if (dialogLayoutWidth < MIN_WIDTH_TO_DISPLAY_IMAGE) {
                    displayPicture.setFitWidth(imageFitWidth);
                } else {
                    displayPicture.setFitWidth(DEFAULT_IMAGE_WIDTH);
                }
            }
        });
    }

    private void initialiseDialog(String text) {
        dialog.setText(text);
    }

    private void initialiseDisplayPicture(Image image) {
        displayPicture.setImage(image);
    }

    private double calculateAppropriateImageFitWidth(double dialogLayoutWidth) {
        double proportionateImageFitWidth = ENLARGED_DEFAULT_MIN_WIDTH_GRADIENT * dialogLayoutWidth
                + IMAGE_FIT_WIDTH_INTERCEPT;
        return Math.max(proportionateImageFitWidth, PSEUDO_HIDDEN_IMAGE_FIT_WIDTH);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    private static DialogBox getDialog(String text, Image image) {
        assert image != null : "Image cannot be null";
        DialogBox db = new DialogBox(text, image);
        return db;
    }

    static DialogBox getCommandDialog(String text, Image image) {
        DialogBox db = getDialog(text, image);
        String newStyle = db.dialog.getStyle() + COMMAND_DIALOG_BACKGROUND_RADIUS_STYLE;
        newStyle += COMMAND_DIALOG_BACKGROUND_COLOUR_STYLE;
        db.dialog.setStyle(newStyle);
        return db;
    }

    private static DialogBox getResponseDialog(String text, Image image) {
        DialogBox db = getDialog(text, image);
        String newStyle = db.dialog.getStyle() + RESPONSE_DIALOG_BACKGROUND_RADIUS_STYLE;
        db.dialog.setStyle(newStyle);
        db.flip();
        return db;
    }

    static DialogBox getNormalResponseDialog(String text, Image image) {
        DialogBox db = getResponseDialog(text, image);
        String newStyle = db.dialog.getStyle() + NORMAL_RESPONSE_DIALOG_BACKGROUND_COLOUR_STYLE;
        db.dialog.setStyle(newStyle);
        return db;
    }

    static DialogBox getErrorResponseDialog(String text, Image image) {
        DialogBox db = getResponseDialog(text, image);
        String newStyle = db.dialog.getStyle() + ERROR_RESPONSE_DIALOG_BACKGROUND_COLOUR_STYLE;
        db.dialog.setStyle(newStyle);
        return db;
    }
}
