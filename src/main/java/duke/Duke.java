package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Duke {
    public static List<Task> toDo = new ArrayList<>();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image duke = new Image(this.getClass().getResourceAsStream("/images/apollo11.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Cycling_pic.png"));

    private Scanner myScanner = new Scanner(System.in);
    private Parser parser;

    public Duke() {
        parser = new Parser(myScanner);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String output = "";
        try {
            output += parser.parse(input);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }


    static int countTasks() {
        int count = 0;
        for (Task task : toDo) {
            if (!task.isDone) {
                count++;
            }
        }
        return count;
    }

}
