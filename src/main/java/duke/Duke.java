package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

import java.lang.String;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image duke = new Image("https://static.wikia.nocookie.net/undertale/images/0/0b/Burgerpants_face_1.png/revision/latest/smart/width/250/height/250?cb=20160103175652");
    private Image user = new Image("https://static.wikia.nocookie.net/undertale-au-fanon/images/1/1e/Undertale_frisk_Sprite.png/revision/latest?cb=20200624132041");

    private static Ui ui = new Ui();
    private static Storage storage = new Storage();
    private static TaskList taskList = new TaskList();
    public static File file = new File("data/list.txt");

    public static void main(String[] args) {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        storage.checkExistence();

        ui.showFileLocation(file.getAbsolutePath());

        taskList.initialise(file, storage);

        Parser.parse();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

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


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String command) {

        taskList.initialise(file, storage);
        if (command.equals("bye")) {
            return "Have a SPARKELOUS day.";
        } else if (command.equals("list")) {

            int c = 1;
            String output = "";
            for (String line : TaskList.lines) {
                output += (c + ". " + line + System.lineSeparator());
                c++;
            }
            if (c == 1) {
                return "The list is empty. How tragic.";
            }
            return output;

        } else if (command.contains("done")) {
            String numbers = command.substring(5);
            try {
                int taskNo = Integer.parseInt(numbers);
            } catch (NumberFormatException notANumber) {
                System.err.println(notANumber);
                return "HELLO SIR I REQUIRE A NUMBER";
            }
            int taskNo = Integer.parseInt(numbers);
            if (TaskList.lines.size() < taskNo) {
                return "HELLO SIR THAT'S TOO MUCH";
            }
            if (taskNo <= 0) {
                return "HI SIR NO NON-POSITIVE INTEGERS ALLOWED";
            }
            taskNo--;
            String toBeDone = TaskList.lines.get(taskNo);
            boolean notAlreadyDone = taskList.makeDone(storage, taskNo);
            if(notAlreadyDone){
                return "Task " + toBeDone + " is now marked as done. Well done.";
            }else{
                return "It is ALREADY DONE WOW!!!!!!";
            }
        } else if (command.contains("delete") || command.contains("remove")) {
            String numbers = command.substring(7);
            try {
                int taskNo = Integer.parseInt(numbers);
            } catch (NumberFormatException notANumber) {
                System.err.println(notANumber);
                return "THAT'S NOT A NUMBER";
            }
            int taskNo = Integer.parseInt(numbers);
            if (TaskList.lines.size() < taskNo) {
                return "THAT'S TOO MUCH";
            }
            if (taskNo <= 0) {
                return "HI SIR NO NON-POSITIVE INTEGERS ALLOWED";
            }
            taskNo--;
            String toBeDeleted = TaskList.lines.get(taskNo);
            taskList.delete(storage, taskNo);
            return "Task" + toBeDeleted + " has been deleted. How sad.";

        } else if (command.contains("todo")) {
            String task = command.substring(5);
            if (task.equals("")) {
                return "I NEED A NAME SIR!!!";
            }
            ToDo taskToAdd = new ToDo(task);

            String toBeAdded = taskToAdd.toString();
            boolean notAlreadyInside = taskList.add(storage, toBeAdded);
            try {
                storage.writeListToFile(Duke.file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(notAlreadyInside) {
                return "Task " + toBeAdded + " has been added. SPARKTASTIC.";
            }else{
                return "It is already inside. WOWZA!";
            }


        } else if (command.contains("deadline")) {
            String taskNDate = command.substring(9);
            if (!(taskNDate.contains("/by"))) {
                return "BY WHEN? I DONT KNOW AHHHHHHHHHHH";
            }
            int splitIndex = taskNDate.indexOf("/by");
            String task = taskNDate.substring(0, splitIndex - 1);
            String date = taskNDate.substring(splitIndex + 4);
            if (task.equals("")) {
                return "WHATS THE NAME OF THE THING SIR";
            }

            try {
                LocalDate test = LocalDate.parse(date);
            } catch (DateTimeException error) {
                return "That is not a date. Please enter in YYYY-MM-DD format. " +
                        "So sorry to inconvenience you.";
            }

            Deadline taskToAdd = new Deadline(task, date);
            String toBeAdded = taskToAdd.toString();
            boolean notAlreadyInside = taskList.add(storage, toBeAdded);
            try {
                storage.writeListToFile(Duke.file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(notAlreadyInside) {
                return "Task " + toBeAdded + " has been added. SPARKTASTIC.";
            }else{
                return "It is already inside. WOWZA!";
            }

        } else if (command.contains("event")) {
            String taskNDate = command.substring(6);
            if (!(taskNDate.contains("/at"))) {
                return "HELP THERES NO DATE please enter in YYYY-MM-DD format. " +
                        "Thank you for your kind cooperation.";
            }
            int splitIndex = taskNDate.indexOf("/at");
            String task = taskNDate.substring(0, splitIndex - 1);
            String date = taskNDate.substring(splitIndex + 4);
            if (task.equals("")) {
                return "WHATS THE NAME OF THE THING";
            }

            try {
                LocalDate test = LocalDate.parse(date);
            } catch (DateTimeException error) {
                return "WHATS THE DATE WHAT HAPPENED";
            }

            Deadline taskToAdd = new Deadline(task, date);
            String toBeAdded = taskToAdd.toString();
            boolean notAlreadyInside = taskList.add(storage, toBeAdded);
            try {
                storage.writeListToFile(Duke.file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(notAlreadyInside) {
                return "Task " + toBeAdded + " has been added. SPARKTASTIC.";
            }else{
                return "It is already inside. WOWZA!";
            }

        } else if (command.contains("find")) {
            String searchQuery=command.substring(5);
            int c = 1;
            String output = "Here are the search results for \"" + searchQuery + "\":" + System.lineSeparator();
            for (String task : TaskList.lines) {
                if (task.contains(searchQuery)) {
                    output += c + ". " + task + System.lineSeparator();
                }
                c++;
            }
            return output;

        } else if (command.equals("WIPE")) {
            return "BAM! The list has been wiped.";
        } else {
            return "(WHAT IS THIS PERSON TRYING TO SAY WHY IS HE TYPING GIBBERISH I'M JUST TRYING TO SURVIVE)";
        }
    }

}
