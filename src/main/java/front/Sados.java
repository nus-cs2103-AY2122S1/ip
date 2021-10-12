package front;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import back.Deadline;
import back.Event;
import back.Storage;
import back.Task;
import back.Todo;
import back.Ui;
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
        Image saveIcon = loadImage("/images/save.png");
        save.setGraphic(new ImageView(saveIcon));

        save.setOnAction(a -> {
            ArrayList<Task> list = new ArrayList<>(tasks);
            Storage.saveToFile(list);
        });
        Button load = new Button("Load");
        Image loadIcon = loadImage("/images/load.png");
        load.setGraphic(new ImageView(loadIcon));
        load.setOnAction(a -> {
            loadSave();
        });
        saveLoad.getChildren().addAll(save, load);
        saveLoad.setSpacing(10);

        //top center text
        Label nowDate = new Label();
        nowDate.setText("Hello! Today's date is: " + Ui.printDate(LocalDate.now()));

        //top right buttons
        HBox deleteDone = new HBox();
        Button delete = new Button("Delete");
        Image deleteIcon = loadImage("/images/delete.png");
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
        Image doneIcon = loadImage("/images/done.png");
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
        Image goIcon = loadImage("/images/go.png");
        go.setGraphic(new ImageView(goIcon));

        //text inputs parsing
        go.setOnAction(e -> {
            String option = actions.getValue();

            String nameInputString = nameInput.getText();
            boolean nameInputIsEmpty = nameInputString.isEmpty();
            String dateInputString = dateInput.getText();
            boolean dateInputIsEmpty = dateInputString.isEmpty();
            LocalDate date = LocalDate.of(1, 1, 1);
            if (!dateInputIsEmpty) {
                try {
                    date = LocalDate.parse(dateInputString);
                } catch (DateTimeParseException err) {
                    Popup.errorPopup("Date format error! (YYYY-MM-DD)");
                }
            }
            switch (option) {
            case "Todo":
                if (nameInputIsEmpty) {
                    Popup.errorPopup("Name field is empty!");
                } else {
                    Todo add = new Todo(nameInputString);
                    addTask(add);
                }
                break;
            case "Event":
                if (nameInputIsEmpty || dateInputIsEmpty) {
                    Popup.errorPopup("Name and/or Date fields are empty!");
                } else {
                    Event add = new Event(nameInputString, date);
                    addTask(add);
                }
                break;
            case "Deadline":
                if (nameInputIsEmpty || dateInputIsEmpty) {
                    Popup.errorPopup("Name and/or Date fields are empty!");
                } else {
                    Deadline add = new Deadline(nameInputString, date);
                    addTask(add);
                }
                break;
            default:
                if (nameInputIsEmpty && dateInputIsEmpty) {
                    Popup.errorPopup("Name and Date fields are empty!");
                } else if (nameInputIsEmpty) {
                    filterResults(2, "", date);
                } else if (dateInputIsEmpty) {
                    filterResults(1, nameInputString, date);
                } else {
                    filterResults(3, nameInputString, date);
                }
                break;
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
     * Loads an image.
     * @param location Location of the image.
     * @return Image
     */
    private Image loadImage(String location) {
        return new Image(getClass().getResourceAsStream(location), 16, 16, true, true);
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
     * Loads tasks from save file.
     */
    private void loadSave() {
        File saveFile = new File("./save.txt");
        try {
            tasks.clear();
            tasks.addAll(Storage.loadFile(saveFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

        switch(type) {
        case 1:
            filterName(list, nameString);
            Popup.filterPopup(list, nameString);
            break;
        case 2:
            filterDate(list, date);
            Popup.filterPopup(list, dateString);
            break;
        default:
            filterNameAndDate(list, nameString, date);
            filterString = nameString + " + " + dateString;
            Popup.filterPopup(list, filterString);
            break;
        }

    }

    /**
     * Adds task to list if it contains the parameter string in it's name
     * @param list Task list to be added to.
     * @param nameString Parameter string.
     */
    private void filterName(ObservableList<String> list, String nameString) {
        for (Task i : tasks) {
            addIfMatchName(i, list, nameString);
        }
    }

    /**
     * Adds task to list if it is on the same date as the parameter date.
     * @param list Task list to be added to.
     * @param date LocatDate object that is to be compared.
     */
    private void filterDate(ObservableList<String> list, LocalDate date) {
        for (Task i : tasks) {
            if (i instanceof Todo) {
                break;
            }
            addIfMatchDate(i, list, date);
        }
    }

    /**
     * Adds task to list if it contains the parameter string in it's name and is on the parameter date.
     * @param list Task list to be added to.
     * @param nameString Parameter string.
     * @param date LocatDate object that is to be compared.
     */
    private void filterNameAndDate(ObservableList<String> list, String nameString, LocalDate date) {
        for (Task i : tasks) {
            if (i instanceof Todo) {
                break;
            }
            if (i.queryIfNameContains(nameString)) {
                addIfMatchDate(i, list, date);
            }
        }
    }

    /**
     * Adds task to list if it shares the same date as the parameter date.
     * @param t Task to be checked.
     * @param list List to be added to.
     * @param date Date to be checked.
     */
    private void addIfMatchDate(Task t, ObservableList<String> list, LocalDate date) {
        if (t instanceof Event) {
            Event e = (Event) t;
            if (e.queryIfDateEquals(date)) {
                list.add(e.toString());
            }
        } else {
            Deadline d = (Deadline) t;
            if (d.queryIfDateEquals(date)) {
                list.add(d.toString());
            }
        }
    }

    /**
     * Adds task to list if it contains the parameter name.
     * @param t Task to be checked.
     * @param list List to be added to.
     * @param nameString String to be checked.
     */
    private void addIfMatchName(Task t, ObservableList<String> list, String nameString) {
        if (t.queryIfNameContains(nameString)) {
            list.add(t.toString());
        }
    }
}
