package duke.ui;

import java.util.List;

import duke.task.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Represents the task list panel for the application.
 */
public class TaskListPane extends BorderPane {
    /**
     * Refreshes the task list panel to display a new list of tasks.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void showTaskList(List<Task> taskList) {
        this.getChildren().clear();
        if (taskList.isEmpty()) {
            this.setCenter(new Label("You do not have any tasks"));
        } else {
            VBox vBox = new VBox();
            for (int i = 0; i < taskList.size(); i++) {
                vBox.getChildren().add(new TaskPane(i + 1, taskList.get(i)));
            }
            this.setCenter(vBox);
        }
    }
}
