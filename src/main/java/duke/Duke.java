package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    Scanner myScanner = new Scanner(System.in);


    private Parser parser;


    public Duke() {
        parser = new Parser(myScanner);
    }

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Cycling_pic.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/apollo11.png"));

//    @Override
//    public void start(Stage stage) {
//        //Step 1. Setting up required components
//
//        //The container for the content of the chat to scroll.
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
//
//        //Step 2. Formatting the window to look as expected
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
//        //Step 3. Add functionality to handle user input.
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
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        //Part 3. Add functionality to handle user input.
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
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String output = "";
        try {
            output += parser.parse(input);
//                if (parser.getBreak()) {
//                    break;
//                }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }

    static List<Task> toDo = new ArrayList<>();

    static int countTasks() {
        int count = 0;
        for (Task task : toDo) {
            if (!task.isDone) {
                count++;
            }
        }
        return count;
    }

    static void saveTasks(List<Task> tasks) {//Called on "bye"
        try {
            File data_file = new File("Data/DukeData.txt");
            FileWriter writer = new FileWriter("Data/DukeData.txt");//Overwriting entire file
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.write("\n");
            }
            writer.close();
            if (data_file.createNewFile()) {
                System.out.println("File created: " + data_file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currLine = s.nextLine();
//            System.out.println(currLine);
            char taskType = currLine.charAt(1);
//            System.out.println(taskType);
            switch (taskType) {
                case 'T':
                    toDo.add(new ToDo(currLine.substring(7)));
                    System.out.println(new ToDo(currLine.substring(7)));
                    break;
                case 'D':
                    int l = currLine.indexOf("(");
//                    int m = currLine.indexOf("by ");
                    int n = currLine.indexOf(")");
//                    System.out.println(currLine.substring(7,l));
//                    System.out.println(currLine.substring(l+1,n));
                    toDo.add(new Deadline(currLine.substring(7, l), currLine.substring(l + 1, n)));
                    System.out.println(new Deadline(currLine.substring(7, l), currLine.substring(l + 1, n)));
                    break;
                case 'E':
                    int i = currLine.indexOf("(");
//                    int j = currLine.indexOf("at ");
                    int k = currLine.indexOf(")");
//                    System.out.println(currLine.substring(7,i));
//                    System.out.println(currLine.substring(j,k));
                    toDo.add(new Event(currLine.substring(7, i), currLine.substring(i + 1, k)));
                    System.out.println(new Event(currLine.substring(7, i), currLine.substring(i + 1, k)));
                    break;
            }
        }
    }

//    public void run() {
//
//        Ui.welcomeUser();
//        while (myScanner.hasNext()) {
//            try {
//                String input = myScanner.nextLine();//Parse the line
//                parser.parse(input);
//                if (parser.getBreak()) {
//                    break;
//                }
//            } catch (DukeException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        myScanner.close();
//    }
//
//    public static void main(String[] args) {
//        new Duke().run();
//    }
}
