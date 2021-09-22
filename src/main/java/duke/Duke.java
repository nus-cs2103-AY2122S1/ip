package duke;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Represents the Duke chatbot. The chatbot currently acts as a to-do list that can receives3 different types of tasks.
 * You can mark the tasks as done and delete them when you're done with the tasks.
 */
public class Duke {

    private static Parser parser = new Parser();
    private duke.Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image alexa = new Image(this.getClass().getResourceAsStream("/images/Alexa.png"));


    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        ui = new Ui();
        storage = new duke.Storage("data/alexa.txt");
        try {
            tasks = new TaskList(duke.Storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
//        new Duke().run();
    }

    /**
     * Runs the Duke chatbot.
     * It is actively waiting for inputs from the User in order to perform its functions.
     */
//    public static void run() {
//        Scanner newInput = new Scanner(System.in);
//        while (newInput.hasNextLine()) {
//            String input = newInput.nextLine();
//            duke.Task currentTask = new duke.Task(input);
//            try {
//                if (input.equals("bye")) {
//                    break;
//                } else if (input.equals("list")) {
//                    Ui.list();
//                } else if (parser.parseDone(input)) {
//                    Ui.done(input);
//                } else if (parser.parseFind(input)) {
//                    Ui.find(input);
//                } else if (parser.parseToDo(input)) {
//                    TaskList.todo(input);
//                } else if (parser.parseDeadline(input)) {
//                    TaskList.deadline(input);
//                } else if (parser.parseEvent(input)) {
//                    TaskList.event(input);
//                } else if (parser.parseDelete(input)) {
//                    TaskList.delete(input);
//                 } else {
//                    Ui.invalidInput();
//                }
//                duke.Storage.writeTasks();
//            } catch (DukeException err) {
//                Ui.print(err.getMessage());
//            }
//        }
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            String text = "";
            if (input.equals("bye")) {
                text = "Bye. Hope to see you again soon!";
            } else if (input.equals("list")) {
                text = Ui.list();
            } else if (parser.parseDone(input)) {
                text = Ui.done(input);
            } else if (parser.parseFind(input)) {
                text = Ui.find(input);
            } else if (parser.parseToDo(input)) {
                text = TaskList.todo(input);
            } else if (parser.parseDeadline(input)) {
                text = TaskList.deadline(input);
            } else if (parser.parseEvent(input)) {
                text = TaskList.event(input);
            } else if (parser.parseDelete(input)) {
                text = TaskList.delete(input);
            } else {
                Ui.invalidInput();
                text = "Sorry, please enter a valid command!";
            }
            duke.Storage.writeTasks();
            return text;
        } catch (DukeException err) {
            return err.getMessage();
        }
    }
}
