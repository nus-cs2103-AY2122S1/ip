package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.classes.TaskList;
import duke.exceptions.DukeException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class DukeGui {
    //Duke Assets
    private DukeStorage storage;
    private TaskList taskList;
    private DukeUI ui;
    private DukeParser parser;

    //JavaFX Assets
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Class Constructor
     */
    public DukeGui() {
        //Duke Initialization
        ui = new DukeUI();
        storage = new DukeStorage();

        try {
            taskList = new TaskList(storage.retrieve("localList.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the saved file");
            e.printStackTrace();
            System.exit(0);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        parser = new DukeParser(taskList);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String testResponse(String input) {
        String output;
        try {
            output = parser.parse(input);
            storage.saveList(taskList);
        } catch (DukeException e) {
            output = e.getMessage();
        } catch (DateTimeParseException ex) {
            output = "Date was entered incorrectly, use yyyy-MM-dd";
        } catch (IOException e) {
            output = "A file error occurred.";
        }
        return output;
    }
}
