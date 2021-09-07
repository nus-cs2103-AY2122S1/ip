package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import commands.Command;
import commands.DueCommand;
import commands.RescheduleCommand;
import duke.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;

/**
 * The DukeApp class implements the GUI for Duke.
 */
public final class DukeApp extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private TaskList lst;
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private Stage stage;
    private UserPrompts userPrompts;

    /**
     * Constructs a DukeApp object.
     */
    public DukeApp() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            storage.createFiles();
        } catch (DukeException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(new Label("something went wrong: "
                            + e.getMessage() + "\n" + "     exiting program...")));
            System.exit(0);
        }
        lst = new TaskList(storage.loadSaves());
        parser = new Parser();

    }

    //@@author wanyu-l-reused
    //Reused from https://se-education.org/guides/tutorials/javaFxPart3.html
    // with minor modifications

    /**
     * Starts the window for the application.
     * @param stage input
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.userPrompts = new UserPrompts();
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Enter");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Memo-Assistant Duke");
        stage.setResizable(false);
        stage.setMinHeight(800.0);
        stage.setMinWidth(1000.0);

        mainLayout.setPrefSize(1000.0, 800.0);

        scrollPane.setPrefSize(985, 735);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(false);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(925.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //@@author

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(new Label(Ui.getWelcomeMessage())));

        ArrayList<String> checkForDue = new ArrayList<>();
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        checkForDue.add("due");
        checkForDue.add(today);
        String itemsDueToday = new DueCommand(checkForDue).execute(lst, ui, storage);
        if (!itemsDueToday.equals("     No tasks due!")) {
            if (itemsDueToday.lines().count() < 5) {
                showDueTasks(itemsDueToday);
            } else {
                ArrayList<String> firstFive = (ArrayList<String>) itemsDueToday.lines()
                        .limit(5).collect(Collectors.toList());
                String truncate = "";
                for (String s : firstFive) {
                    truncate += s + "\n";
                }
                truncate += "     ...\n" + "     For full output, please click confirm and use Due command.";
                showDueTasks(truncate);
            }
        }
    }

    private void showDueTasks(String tasksFound) {
        Stage exitWindow = new Stage();
        exitWindow.setTitle("There are task(s) due today!");
        exitWindow.setWidth(400);
        exitWindow.setHeight(220);
        exitWindow.setResizable(false);
        exitWindow.setAlwaysOnTop(false);

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
        for (int i = 0; i < lst.getTasks().size(); i++) {
            try {
                if (lst.getTasks().get(i).getLocalDate().equals(LocalDate.now())) {
                    ArrayList<String> toSnooze = new ArrayList<>();
                    toSnooze.add("reschedule");
                    toSnooze.add(String.valueOf(i + 1));
                    toSnooze.add(getTomorrow());
                    String result = new RescheduleCommand(toSnooze).execute(lst, ui, storage);
                    dialogContainer.getChildren().add(DialogBox.getDukeDialog(new Label(result)));
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
    }

    private String getTomorrow() {
        return LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private void terminateSession() {
        stage.close();
    }

    private void handleUserInput() {
        String inputText = userInput.getText();
        Command c = new Parser().parse(inputText);
        if (c != null) {
            Label userText = new Label(inputText);
            String executeResult = c.execute(lst, ui, storage);
            Label dukeText = new Label(getResponse(executeResult));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText),
                    DialogBox.getDukeDialog(dukeText)
            );
            if (c.isExit()) {
                userPrompts.showExitMessage();
                terminateSession();
            }
        } else {
            dialogContainer.getChildren().addAll(DialogBox.getUserDialog(new Label(inputText)),
                    DialogBox.getDukeDialog(new Label(Ui.getHelperMessage())));
        }
        userInput.clear();
    }

    private String getResponse(String input) {
        if (input.equals("")) {
            return input;
        }
        return ui.getSeparator() + "\n"
                + input + "\n"
                + ui.getSeparator();
    }
}
