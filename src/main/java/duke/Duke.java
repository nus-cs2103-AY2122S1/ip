package duke;

import duke.command.Command;

/**
 * Interactive bot that manages one's tasks.
 */
public class Duke {
    private FileManager filemanager;
    private Tasklist tasks;
    private Ui ui;
    private static final String defaultFilePath = "taskList.txt";

    /**
     * Constructs Duke which stores the tasks in a file.
     *
     * @param filePath filepath for the file which the tasks will be solved in.
     */
    public Duke(String filePath) {
        ui = new Ui();
        filemanager = new FileManager(filePath);
        tasks = new Tasklist(filemanager.getTaskList());
    }

    public Duke() {
        ui = new Ui();
        filemanager = new FileManager(defaultFilePath);
        tasks = new Tasklist(filemanager.getTaskList());
    }

//    /**
//     * Runs Duke to manage user's task.
//     */
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, filemanager);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e);
//            }
//        }
//    }

//    /**
//     * To run Duke.
//     *
//     * @param args no arguments
//     */
//    public static void main(String[] args) {
//        new Duke("taskList.txt").run();
//    }

//    @Override
//    public void start(Stage stage) {
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput, 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//    }
//
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     *
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//        return textToAdd;
//    }
//
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }
//
    public String sayHi() {
        return this.ui.showWelcome();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String uiStr = c.execute(tasks, ui, filemanager);
            return uiStr;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

