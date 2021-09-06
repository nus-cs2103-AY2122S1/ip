package head;

import addon.Popup;
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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A simple task tracking interface.
 */

public class Sados extends Application {

    private final ObservableList<Task> tasks = FXCollections.observableArrayList();
    private final ListView<Task> listView = new ListView<>(tasks);

    @Override
    public void start(Stage stage) {

        stage.setTitle("SaDOS");

        HBox saveLoad = new HBox(); //top left
        Button save = new Button("Save");
        save.setOnAction(a -> {
            ArrayList<Task> list = new ArrayList<>(tasks);
            Storage.saveToFile(list);
        });
        Button load = new Button("Load");
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

        Label nowDate = new Label(); //top center
        nowDate.setText("Hello! Today's date is: " + Ui.printDate(LocalDate.now()));

        HBox deleteDone = new HBox(); //top right
        Button delete = new Button("Delete");
        delete.setOnAction(e -> {
            Task selected = listView.getSelectionModel().getSelectedItem();
            boolean isConfirm = Popup.confirmationPopup(selected.toString());
            if (isConfirm) {
                tasks.remove(selected);
            }
        });
        Button done = new Button("Done");
        done.setOnAction(e -> {
            Task selected = listView.getSelectionModel().getSelectedItem();
            selected.markDone();
            listView.refresh();
        });
        deleteDone.getChildren().addAll(delete, done);
        deleteDone.setSpacing(10);

        BorderPane topPanel = new BorderPane(); //full top row
        topPanel.setPadding(new Insets(10, 10, 10, 10));
        topPanel.setLeft(saveLoad);
        topPanel.setCenter(nowDate);
        topPanel.setRight(deleteDone);

        TextField nameInput = new TextField();
        nameInput.setPromptText("Task Name");
        TextField dateInput = new TextField();
        dateInput.setPromptText("Date (YYYY-MM-DD)");

        ChoiceBox<String> actions = new ChoiceBox<>();
        actions.getItems().addAll("Todo", "Event", "Deadline", "Search");
        actions.getSelectionModel().select(0);

        Button go = new Button("Go!"); //parsing information from inputs
        go.setOnAction(e -> {
            String option = actions.getValue();
            if (option.equals("Todo")) {
                if (nameInput.getText().isEmpty()) {
                    Popup.errorPopup("Name field is empty!");
                } else {
                    Todo add = new Todo(nameInput.getText());
                    nameInput.clear();
                    tasks.add(add);
                }
            } else if (option.equals("Search") && dateInput.getText().isEmpty()) {
                if (nameInput.getText().isEmpty()) {
                    Popup.errorPopup("Name and Date fields are empty!");
                } else {
                    String searchName = nameInput.getText();
                    filter(1, searchName, LocalDate.of(1, 1, 1));
                }
            } else {
                if (dateInput.getText().isEmpty()) {
                    if (nameInput.getText().isEmpty()) {
                        Popup.errorPopup("Name and Date fields are empty!");
                    } else {
                        Popup.errorPopup("Date field is empty!");
                    }
                } else {
                    LocalDate date;
                    try {
                        date = LocalDate.parse(dateInput.getText());
                        if (option.equals("Search")) {
                            if (nameInput.getText().isEmpty()) {
                                filter(2, "", date);
                            } else {
                                String searchName = nameInput.getText();
                                filter(3, searchName, date);
                            }
                        } else if (option.equals("Event")) {
                            if (nameInput.getText().isEmpty()) {
                                Popup.errorPopup("Name field is empty!");
                            } else {
                                Event add = new Event(nameInput.getText(), date);
                                nameInput.clear();
                                dateInput.clear();
                                tasks.add(add);
                            }
                        } else {
                            if (nameInput.getText().isEmpty()) {
                                Popup.errorPopup("Name field is empty!");
                            } else {
                                Deadline add = new Deadline(nameInput.getText(), date);
                                nameInput.clear();
                                dateInput.clear();
                                tasks.add(add);
                            }
                        }
                    } catch (DateTimeParseException err) {
                        Popup.errorPopup("Date format error! (YYYY-MM-DD)");
                    }
                }
            }
        });

        HBox bottomPanel = new HBox(); //full bottom row
        bottomPanel.setPadding(new Insets(10, 10, 10, 10));
        bottomPanel.setSpacing(10);
        bottomPanel.getChildren().addAll(nameInput, dateInput, actions, go);
        HBox.setHgrow(nameInput, Priority.ALWAYS);
        HBox.setHgrow(dateInput, Priority.ALWAYS);
        HBox.setHgrow(actions, Priority.ALWAYS);
        HBox.setHgrow(go, Priority.ALWAYS);

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        BorderPane borders = new BorderPane(); //full window
        borders.setTop(topPanel);
        borders.setCenter(listView);
        borders.setBottom(bottomPanel);

        Scene home = new Scene(borders, 500, 250);
        stage.setScene(home);
        stage.show();
    }

    /**
     * Opens a popup window with the filtered items.
     *
     * @param type 1 if filtering only by name, 2 if filtering only by date, 3 if by both.
     * @param name String name if type 1 or 3.
     * @param date LocalDate object if type 2 or 3.
     */
    private void filter(int type, String name, LocalDate date) {

        ObservableList<String> list = FXCollections.observableArrayList();
        String dateString = Ui.printDate(date);
        String filterString;

        if (type == 1) { //name only
            for (Task i : tasks) {
                if (i.nameContains(name)) {
                    list.add(i.toString());
                }
            }
            filterString = name;
        } else if (type == 2) { //date only
            for (Task i : tasks) {
                if (i instanceof Event) {
                    Event e = (Event) i;
                    if (e.dateEquals(date)) {
                        list.add(i.toString());
                    }
                } else if (i instanceof Deadline) {
                    Deadline d = (Deadline) i;
                    if (d.dateEquals(date)) {
                        list.add(i.toString());
                    }
                }
            }
            filterString = dateString;
        } else { //name and date
            for (Task i : tasks) {
                if (i.nameContains(name)) {
                    if (i instanceof Event) {
                        Event e = (Event) i;
                        if (e.dateEquals(date)) {
                            list.add(i.toString());
                        }
                    } else if (i instanceof Deadline) {
                        Deadline d = (Deadline) i;
                        if (d.dateEquals(date)) {
                            list.add(i.toString());
                        }
                    }
                }
            }
            filterString = name + " + " + dateString;
        }
        Popup.filterPopup(list, filterString);
    }
}
