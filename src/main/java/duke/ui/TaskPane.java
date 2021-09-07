package duke.ui;

import java.time.LocalDate;

import duke.task.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Represents the individual task panel describing the task.
 */
public class TaskPane extends AnchorPane {
    /**
     * Constructs a TaskPane.
     *
     * @param serialNo The serial number for the task
     * @param task The task to display.
     */
    public TaskPane(int serialNo, Task task) {
        Label taskDescription = new Label(
                String.format("%d. %s: %s", serialNo, task.getTaskType().getLabel(), task.getDescription())
        );
        Label statusDescription = new Label();
        if (task.isDone()) {
            statusDescription.setText("DONE");
            statusDescription.setTextFill(Color.GREEN);
        } else {
            statusDescription.setText("NOT DONE");
            statusDescription.setTextFill(Color.RED);
        }
        LocalDate date = task.getDate();
        Label dateLabel = date != null ? new Label(String.format("Date: %s %d %d", date.getMonth(),
                date.getDayOfMonth(), date.getYear())) : new Label("Date: Not Applicable");
        this.getChildren().addAll(taskDescription, dateLabel, statusDescription);
        AnchorPane.setTopAnchor(taskDescription, 10.0);
        AnchorPane.setLeftAnchor(taskDescription, 10.0);
        AnchorPane.setTopAnchor(dateLabel, 10.0);
        AnchorPane.setRightAnchor(dateLabel, 10.0);
        AnchorPane.setTopAnchor(statusDescription, 30.0);
        AnchorPane.setBottomAnchor(statusDescription, 10.0);
        AnchorPane.setRightAnchor(statusDescription, 10.0);
        setBorder(
                new Border(
                        new BorderStroke(
                                null, null, Color.LIGHTGREY, null,
                                null, null, BorderStrokeStyle.SOLID, null,
                                CornerRadii.EMPTY, BorderStroke.THIN, Insets.EMPTY
                        )
                )
        );
    }
}
