package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

public class GUI extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) {
        Launcher.main(args);
    }

    @Override
    public void start(Stage stage) {
    }

/*
    @Override
    public void start(Stage stage) {
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
    }*/

    public String getResponse(String s) {
        return "Dude heard : " + s;
    }

    public String run() {
        String logo2 = " ____            _      \n"
                + "|  _ \\ _   _  __| | ___ \n"
                + "| | | | | | |/ _` |/ _ \\\n"
                + "| |_| | |_| | (_| |  __/\n"
                + "|____/ \\__,_|\\__,_|\\___|\n";

        System.out.println("Hello from\n" + logo2);
        System.out.println("Hello! I'm Dude");

        Scanner stdIn = new Scanner(System.in);
        boolean isRunning = true;

        DataFile dataFile = new DataFile("./duke_data.txt");
        TaskList taskList = new TaskList(dataFile);
        System.out.println("What can I do for you?");

        while (isRunning) {
            String input = Duke.getPrompt(stdIn);
            isRunning = Duke.processInput(input, taskList);
        }

        return "";
    }

    
}