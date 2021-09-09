package duke;

import java.time.LocalDate;

import duke.exception.DukeStorageException;
import duke.task.DukeDeadlineTask;
import duke.task.DukeEvent;
import duke.task.DukeSimpleTask;
import duke.task.DukeTask;
import duke.task.TaskList;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Represents the UI interactions in the program.
 */
public class Gui implements Ui {
    private final Storage storage;
    private final AnchorPane rootPane;
    private final Button addButton;
    private final DatePicker deadlinePicker;
    private final DatePicker datePicker;
    private final TableView<DukeTask> taskTableView;
    private TaskList taskList;
    private final TextField nameField;

    /**
     * Creates a new user interface.
     * @param storage The storage containing the task list
     */
    public Gui(Storage storage) {
        this.storage = storage;
        this.rootPane = new AnchorPane();
        this.addButton = new Button("Add");
        this.addButton.setOnAction(this::addTask);
        this.deadlinePicker = new DatePicker();
        this.deadlinePicker.setPromptText("Deadline");
        this.datePicker = new DatePicker();
        this.datePicker.setPromptText("Date");
        this.nameField = new TextField();
        this.nameField.setPromptText("Task name");
        this.taskTableView = new TableView<>();
        try {
            this.taskList = storage.loadTaskList();
        } catch (DukeStorageException e) {
            e.printStackTrace();
        }
        initializeUi();
    }

    private Node getAddTaskSection() {
        Label sectionLabel = createSectionLabel("Add task");

        // Initialize buttons
        RadioButton deadlineButton = new RadioButton("By");
        deadlineButton.setFocusTraversable(false);

        RadioButton dateButton = new RadioButton("At");
        dateButton.setFocusTraversable(false);

        // Bind button properties so that they are selected when the
        // corresponding date pickers are focused/contain a value.
        dateButton.selectedProperty().bind(
                Bindings.and(Bindings.not(deadlinePicker.focusedProperty()),
                        Bindings.or(datePicker.focusedProperty(), Bindings.isNotNull(datePicker.valueProperty())))
        );
        deadlineButton.selectedProperty().bind(
                Bindings.and(Bindings.not(datePicker.focusedProperty()),
                        Bindings.or(deadlinePicker.focusedProperty(),
                                Bindings.isNotNull(deadlinePicker.valueProperty())))
        );

        // When a date is chosen, clear the other date picker
        deadlinePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                datePicker.setValue(null);
            }
        });
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deadlinePicker.setValue(null);
            }
        });

        VBox deadlineGroup = new VBox(deadlineButton, deadlinePicker);
        deadlineGroup.setSpacing(4.0);

        VBox dateGroup = new VBox(dateButton, datePicker);
        dateGroup.setSpacing(4.0);

        HBox dateInformationGroup = new HBox(deadlineGroup, dateGroup);
        dateInformationGroup.setSpacing(8.0);

        VBox vBox = new VBox(sectionLabel, nameField, dateInformationGroup, addButton);
        vBox.setSpacing(8.0);
        return vBox;
    }

    private Label createSectionLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font(16));
        return label;
    }

    private Node getTaskTableSection() {
        Label sectionLabel = createSectionLabel("Tasks");

        taskTableView.getItems().setAll(taskList.getTasks());
        taskTableView.setEditable(true);

        TableColumn<DukeTask, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory((features) -> features.getValue().nameProperty());
        nameColumn.setEditable(true);

        TableColumn<DukeTask, Boolean> isDoneColumn = new TableColumn<>("Done");
        isDoneColumn.setCellFactory(
                CheckBoxTableCell.forTableColumn((index) -> taskList.getTaskAt(index).isDoneProperty()));
        isDoneColumn.setEditable(true);

        TableColumn<DukeTask, String> dateColumn = new TableColumn<>("Deadline/Date");
        dateColumn.setCellValueFactory((features) -> {
            DukeTask task = features.getValue();
            if (task instanceof DukeSimpleTask) {
                return new SimpleStringProperty("");
            } else if (task instanceof DukeDeadlineTask) {
                return new SimpleStringProperty("By " + ((DukeDeadlineTask) task).getDeadline().toString());
            } else {
                return new SimpleStringProperty("At " + ((DukeEvent) task).getDate().toString());
            }
        });

        TableColumn<DukeTask, Button> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setCellFactory(ActionButtonTableCell.forTableColumn("Delete", (task) -> {
            deleteTask(task);
            return task;
        }));

        taskTableView.getColumns().add(isDoneColumn);
        taskTableView.getColumns().add(nameColumn);
        taskTableView.getColumns().add(dateColumn);
        taskTableView.getColumns().add(actionsColumn);

        // Whenever the checkbox changes state, re-save the list
        taskList.getTasks().forEach((task) -> {
            task.isDoneProperty().addListener((newValue) -> {
                storage.saveTaskList(taskList);
            });
        });

        VBox vBox = new VBox(sectionLabel, taskTableView);
        vBox.setSpacing(8.0);
        return vBox;
    }

    /**
     * Adds a task as specified by the GUI elements to the task list.
     * @param actionEvent The action event causing the function to be triggered
     */
    private void addTask(ActionEvent actionEvent) {
        String taskName = nameField.getText();
        LocalDate deadline = deadlinePicker.getValue();
        LocalDate date = datePicker.getValue();

        // Deadline and date cannot be defined at the same time
        assert (deadline == null) || (date == null);

        DukeTask task;
        if (deadline != null) {
            task = new DukeDeadlineTask(taskName, deadline.toString());
        } else if (date != null) {
            task = new DukeEvent(taskName, date.toString());
        } else {
            task = new DukeSimpleTask(taskName);
        }

        taskList.addTask(task);
        storage.saveTaskList(taskList);

        taskTableView.getItems().add(task);
    }

    private void deleteTask(DukeTask task) {
        taskList.removeTaskAt(taskTableView.getItems().indexOf(task));
        storage.saveTaskList(taskList);

        taskTableView.getItems().remove(task);
    }

    private void initializeUi() {
        Label welcomeLabel = new Label("Welcome to Duke - GUI Edition!");
        welcomeLabel.setFont(new Font(24));

        VBox vBox = new VBox(
            welcomeLabel,
            new Separator(),
            getAddTaskSection(),
            new Separator(),
            getTaskTableSection()
        );
        vBox.setSpacing(16.0);
        vBox.setPadding(new Insets(8.0));

        AnchorPane.setBottomAnchor(vBox, 0.0);
        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);
        AnchorPane.setTopAnchor(vBox, 0.0);

        rootPane.getChildren().add(vBox);
        rootPane.setPrefWidth(600);
        rootPane.setPrefHeight(600);
    }

    public Parent getRootNode() {
        return rootPane;
    }
}
