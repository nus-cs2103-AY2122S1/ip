package head;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import addon.Storage;
import addon.Task;
import addon.Task.Deadline;
import addon.Task.Event;
import addon.Task.Todo;
import addon.Ui;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * A simple task tracking interface.
 */

public class Sados extends Application {

    private final ObservableList<Task> tasks = FXCollections.observableArrayList();
    private final ListView<Task> listView = new ListView<>(tasks);

    @Override
    public void start(Stage stage) {

        stage.setTitle("SaDOS");
        Image appIcon = new Image(getClass().getResourceAsStream("/images/cake.png"));
        stage.getIcons().add(appIcon);

        //top left buttons
        HBox saveLoad = new HBox();
        Button save = new Button("Save");
        Image saveIcon = new Image(getClass().getResourceAsStream("/images/save.png"), 16, 16, true, true);
        save.setGraphic(new ImageView(saveIcon));

        save.setOnAction(a -> {
            ArrayList<Task> list = new ArrayList<>(tasks);
            Storage.saveToFile(list);
        });
        Button load = new Button("Load");
        Image loadIcon = new Image(getClass().getResourceAsStream("/images/load.png"), 16, 16, true, true);
        load.setGraphic(new ImageView(loadIcon));
        load.setOnAction(a -> {
            if (new File("./save.txt").isFile()) {
                try {
                    tasks.clear();
                    tasks.addAll(Storage.loadFile(new File("./save.txt")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        saveLoad.getChildren().addAll(save, load);
        saveLoad.setSpacing(10);

        //top center text
        Label nowDate = new Label();
        nowDate.setText("Hello! Today's date is: " + Ui.printDate(LocalDate.now()));

        //top right buttons
        HBox deleteDone = new HBox();
        Button delete = new Button("Delete");
        Image deleteIcon = new Image(getClass().getResourceAsStream("/images/delete.png"), 16, 16, true, true);
        delete.setGraphic(new ImageView(deleteIcon));
        delete.setOnAction(e -> {
            Task selected = listView.getSelectionModel().getSelectedItem();
            boolean isConfirm = Popup.confirmationPopup("Are you sure you want to delete the following task:\n"
                    + selected.toString());
            if (isConfirm) {
                tasks.remove(selected);
            }
        });
        Button done = new Button("Done");
        Image doneIcon = new Image(getClass().getResourceAsStream("/images/done.png"), 16, 16, true, true);
        done.setGraphic(new ImageView(doneIcon));
        done.setOnAction(e -> {
            Task selected = listView.getSelectionModel().getSelectedItem();
            selected.markDone();
            listView.refresh();
        });
        deleteDone.getChildren().addAll(delete, done);
        deleteDone.setSpacing(10);

        //full top row
        BorderPane topPanel = new BorderPane();
        topPanel.setPadding(new Insets(10, 10, 10, 10));
        topPanel.setLeft(saveLoad);
        topPanel.setCenter(nowDate);
        topPanel.setRight(deleteDone);

        //bottom left text inputs
        TextField nameInput = new TextField();
        nameInput.setPromptText("Task Name");
        TextField dateInput = new TextField();
        dateInput.setPromptText("Date (YYYY-MM-DD)");

        //bottom right dropdown list and button
        ChoiceBox<String> actions = new ChoiceBox<>();
        actions.getItems().addAll("Todo", "Event", "Deadline", "Search");
        actions.getSelectionModel().select(0);
        Button go = new Button("Go!");
        Image goIcon = new Image(Sados.class.getResourceAsStream("/images/go.png"), 16, 16, true, true);
        go.setGraphic(new ImageView(goIcon));

        //text inputs parsing
        go.setOnAction(e -> {
            String option = actions.getValue();

            String nameInputString = nameInput.getText();
            boolean nameInputIsEmpty = nameInputString.isEmpty();
            String dateInputString = dateInput.getText();
            boolean dateInputIsEmpty = dateInputString.isEmpty();

            if (option.equals("Todo")) {
                if (nameInputIsEmpty) {
                    Popup.errorPopup("Name field is empty!");
                } else {
                    Todo add = new Todo(nameInputString);
                    addTask(add);
                }
            } else if (option.equals("Search") && dateInputIsEmpty) {
                if (nameInputIsEmpty) {
                    Popup.errorPopup("Name and Date fields are empty!");
                } else {
                    filterResults(1, nameInputString, LocalDate.of(1, 1, 1));
                }
            } else {
                if (dateInputIsEmpty) {
                    if (nameInputIsEmpty) {
                        Popup.errorPopup("Name and Date fields are empty!");
                    } else {
                        Popup.errorPopup("Date field is empty!");
                    }
                } else {
                    LocalDate date;
                    try {
                        date = LocalDate.parse(dateInputString);
                        if (option.equals("Search")) {
                            if (nameInputIsEmpty) {
                                filterResults(2, "", date);
                            } else {
                                filterResults(3, nameInputString, date);
                            }
                        } else {
                            if (nameInputIsEmpty) {
                                Popup.errorPopup("Name field is empty!");
                            } else {
                                if (option.equals("Event")) {
                                    Event add = new Event(nameInputString, date);
                                    addTask(add);
                                } else if (option.equals("Deadline")) {
                                    Deadline add = new Deadline(nameInputString, date);
                                    addTask(add);
                                }
                            }
                        }
                    } catch (DateTimeParseException err) {
                        Popup.errorPopup("Date format error! (YYYY-MM-DD)");
                    }
                }
            }
            nameInput.clear();
            dateInput.clear();
        });

        //full bottom row
        HBox bottomPanel = new HBox();
        bottomPanel.setPadding(new Insets(10, 10, 10, 10));
        bottomPanel.setSpacing(10);
        bottomPanel.getChildren().addAll(nameInput, dateInput, actions, go);
        HBox.setHgrow(nameInput, Priority.ALWAYS);
        HBox.setHgrow(dateInput, Priority.ALWAYS);
        HBox.setHgrow(actions, Priority.ALWAYS);
        HBox.setHgrow(go, Priority.ALWAYS);

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //full window
        BorderPane borders = new BorderPane();
        borders.setTop(topPanel);
        borders.setCenter(listView);
        borders.setBottom(bottomPanel);

        Scene home = new Scene(borders, 500, 250);
        home.getRoot().setStyle("-fx-font-family: 'Helvetica'");
        stage.setScene(home);
        stage.show();
    }

    /**
     * Checks for duplicates in the tasklist, and asks if duplicates should be added.
     *
     * @param task Task to be added to the list.
     */
    private void addTask(Task task) {

        boolean canAddTask = true;

        if (tasks.contains(task)) {
            canAddTask = Popup.confirmationPopup("Duplicate task detected. Add anyway?");
        }
        if (canAddTask) {
            tasks.add(task);
        }
    }

    /**
     * Opens a popup window with the filtered items.
     *
     * @param type 1 if filtering only by name, 2 if filtering only by date, 3 if by both.
     * @param nameString String to be checked in the task names if type 1 or 3.
     * @param date LocalDate object representing the date to be checked if type 2 or 3.
     */
    private void filterResults(int type, String nameString, LocalDate date) {

        ObservableList<String> list = FXCollections.observableArrayList();
        String dateString = Ui.printDate(date);
        String filterString;

        //name only
        if (type == 1) {
            for (Task i : tasks) {
                if (i.queryIfNameContains(nameString)) {
                    list.add(i.toString());
                }
            }
            filterString = nameString;
        } else if (type == 2) { //date only
            for (Task i : tasks) {
                if (i instanceof Todo) {
                    break;
                } else if (i instanceof Event) {
                    Event e = (Event) i;
                    if (e.queryIfDateEquals(date)) {
                        list.add(i.toString());
                    }
                } else if (i instanceof Deadline) {
                    Deadline d = (Deadline) i;
                    if (d.queryIfDateEquals(date)) {
                        list.add(i.toString());
                    }
                }
            }
            filterString = dateString;
        } else { //name and date
            for (Task i : tasks) {
                if (i.queryIfNameContains(nameString)) {
                    if (i instanceof Event) {
                        Event e = (Event) i;
                        if (e.queryIfDateEquals(date)) {
                            list.add(i.toString());
                        }
                    } else if (i instanceof Deadline) {
                        Deadline d = (Deadline) i;
                        if (d.queryIfDateEquals(date)) {
                            list.add(i.toString());
                        }
                    }
                }
            }
            filterString = nameString + " + " + dateString;
        }
        Popup.filterPopup(list, filterString);

    }
}
