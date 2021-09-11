package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import commands.Command;
import commands.DueCommand;
import commands.RescheduleCommand;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parser.Parser;

/**
 * Controller for ui.MainWindow. Provides the layout for the other controls.
 */
public final class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final DukeApp duke = new DukeApp();
    private Stage stage;
    private Image userImage;
    private Image dukeImage;
    private Image icon;

    /**
     * Sets the stage as a class field for this instance of MainWindow.
     * @param stage the input Stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        FileInputStream daDuke;
        try {
            daDuke = new FileInputStream("src/main/resources/images/DaDuke.png");
            dukeImage = new Image(daDuke);
        } catch (FileNotFoundException e) {
            System.out.println("daDuke image not found");
        }
        FileInputStream daUser;
        try {
            daUser = new FileInputStream("src/main/resources/images/DaUser.png");
            userImage = new Image(daUser);
        } catch (FileNotFoundException e) {
            System.out.println("daUser image not found");
        }
        FileInputStream dukeMascot;
        try {
            dukeMascot = new FileInputStream("src/main/resources/images/DukeMascot.png");
            icon = new Image(dukeMascot);
        } catch (FileNotFoundException e) {
            System.out.println("daUser image not found");
        }
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.getWelcomeMessage(), dukeImage));
        checkAnyDueToday();
    }

    @FXML
    private void handleUserInput() {
        String inputText = userInput.getText();
        Command c = new Parser().parse(inputText);
        if (c != null && c.isExit()) {
            duke.getUserPrompts().showExitMessage(icon);
            terminateSession();
        }
        if (c != null) {
            String executeResult = c.execute(duke.getLst(), duke.getUi(), duke.getStorage());
            String dukeText = getResponse(executeResult);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(inputText, userImage),
                    DialogBox.getDukeDialog(dukeText, dukeImage));
        } else {
            dialogContainer.getChildren().addAll(DialogBox.getUserDialog(inputText, userImage),
                    DialogBox.getDukeDialog(Ui.getHelperMessage(), dukeImage));
        }
        userInput.clear();
    }

    private void checkAnyDueToday() {
        ArrayList<String> checkForDue = new ArrayList<>();
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        checkForDue.add("due");
        checkForDue.add(today);
        String tasksDue = new DueCommand(checkForDue).execute(duke.getLst(), duke.getUi(), duke.getStorage());
        if (!tasksDue.equals("     No tasks due!")) {
            getDueTasksMessage(tasksDue);
        }
    }

    private void getDueTasksMessage(String tasksDueToday) {
        if (tasksDueToday.lines().count() < 5) {
            showDueTasks(tasksDueToday);
        } else {
            ArrayList<String> firstFive = (ArrayList<String>) tasksDueToday.lines()
                    .limit(5).collect(Collectors.toList());
            String truncatedMessage = "";
            for (String s : firstFive) {
                truncatedMessage += s + "\n";
            }
            truncatedMessage += "     ...\n" + "     For full output, please click confirm and use Due command.";
            showDueTasks(truncatedMessage);
        }
    }

    private void showDueTasks(String tasksFound) {
        Stage exitWindow = new Stage();
        exitWindow.setTitle("There are task(s) due today!");
        exitWindow.setWidth(400);
        exitWindow.setHeight(220);
        exitWindow.setResizable(false);
        exitWindow.setAlwaysOnTop(false);
        exitWindow.getIcons().add(icon);

        Label tasksDue = new Label(tasksFound);
        Button snooze = new Button("Snooze");
        Button confirm = new Button("Confirm");

        HBox userChoice = new HBox(snooze, confirm);

        confirm.setOnAction(event -> exitWindow.close());
        snooze.setOnAction(event -> {
            snooze();
            exitWindow.close();
        });

        VBox information = new VBox(10);
        information.setFillWidth(true);
        information.getChildren().add(tasksDue);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(information, userChoice);

        AnchorPane.setBottomAnchor(userChoice, 30.0);
        AnchorPane.setLeftAnchor(userChoice, 135.0);

        Scene exitScene = new Scene(anchorPane);
        exitWindow.setScene(exitScene);
        exitWindow.showAndWait();
    }

    private void snooze() {
        for (int i = 0; i < duke.getLst().getTasks().size(); i++) {
            boolean hasLocalDate = duke.getLst().getTasks().get(i).getLocalDate() != null;
            if (hasLocalDate && duke.getLst().getTasks().get(i).getLocalDate().equals(LocalDate.now())) {
                ArrayList<String> toSnooze = new ArrayList<>();
                toSnooze.add("reschedule");
                toSnooze.add(String.valueOf(i + 1));
                toSnooze.add(getTomorrow());
                String result = new RescheduleCommand(toSnooze).execute(duke.getLst(),
                        duke.getUi(), duke.getStorage());
                dialogContainer.getChildren().add(DialogBox.getDukeDialog(result, dukeImage));
            }
        }
    }

    private String getTomorrow() {
        return LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String getResponse(String input) {
        return input;
    }

    private void terminateSession() {
        stage.close();
    }
}
