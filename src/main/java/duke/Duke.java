package duke;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.data.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.scene.control.Label;

//todo command to print deadlines/events occuring on specific date
//todo bug of task name/date/time being deleted by one when the program reopens and list keyed in

/**
 * The Duke programme implements a bot that help users to record the tasks they have.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String storageFile;

    private File dataFile;

    private static final String STORAGE_DIRECTORY = "data/";

    public Duke() {} //needed for Application.launch() to work

    public Duke(String storageFile) {
        this.storageFile = storageFile;
        this.dataFile = new File(STORAGE_DIRECTORY + storageFile);
        ui = new Ui();
        storage = new Storage(this.storageFile);
        try {
            boolean isFileCreated = dataFile.createNewFile();
            if (!isFileCreated) {
                taskList = new TaskList(storage.load());
            } else {
                taskList = new TaskList();
            }
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);

        while (!isExit) {
            try {
                String command = ui.readCommand(sc);
                ui.showLine();
                Parser p = new Parser();
                Command c = p.parse(command);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        sc.close();

    }

//    public static void main(String[] args) {
////        new Duke(STORAGE_PATH).run();
//        Application.launch(args);
//    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//
////        private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
////        private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
//
//        FileInputStream backgroundImageiInput = new FileInputStream("C:/Users/Danqi/Pictures/downloaded/0e94e2a545ea20ffad466d92dcdd953a1.jpg");
//        FileInputStream userImageInput = new FileInputStream("C:/Users/Danqi/Pictures/downloaded/347716.jpg");
//        FileInputStream dukeImageInput = new FileInputStream("C:/Users/Danqi/Pictures/downloaded/Alice-in-Wonderland.jpg");
//
//        backgroundImage = new Image(backgroundImageiInput);
//        user = new Image(userImageInput);
//        duke = new Image(dukeImageInput);
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(625.0);
//        stage.setMinWidth(375.0);
//
//        mainLayout.setPrefSize(375.0, 625.0);
//
//        scrollPane.setPrefSize(360, 560);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        //scrollPane.setContent(new ImageView(backgroundImage));
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(310.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
////        sendButton.setOnMouseClicked((event) -> {
////            handleUserInput();
////        });
////
////        userInput.setOnAction((event) -> {
////            handleUserInput();
////        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    //}

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        String userText = userInput.getText();
//        String dukeText = getResponse(userInput.getText());
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, user),
//                DialogBox.getDukeDialog(dukeText, duke)
//        );
//        userInput.clear();
//    }

//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                new DialogBox(userText, new ImageView(user)),
//                new DialogBox(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
