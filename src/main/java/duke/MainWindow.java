package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        duke.setMain(this);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog( duke.greeting(), dukeImage)
        );
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask(){
//            public void run(){
//                System.out.println("complete");
//                Platform.runLater(() -> {
//                    dialogContainer.getChildren().addAll(
//                            DialogBox.getDukeDialog( duke.greeting(), dukeImage)
//                    );
//                });
//            }
//        };
//
//
//        Calendar date = Calendar.getInstance();
//        System.out.println("hello");
//        date.set(Calendar.YEAR,2021);
//        date.set(Calendar.MONTH,Calendar.SEPTEMBER);
//        date.set(Calendar.DAY_OF_MONTH,15);
//        date.set(Calendar.HOUR_OF_DAY,13);
//        date.set(Calendar.MINUTE,47);
//        date.set(Calendar.SECOND,35);
//        date.set(Calendar.MILLISECOND,0);
//        timer.schedule(task, date.getTime());


    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    public void  popReminder(String reminder){
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(reminder, dukeImage)
        );
    }
}
