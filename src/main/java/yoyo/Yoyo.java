package yoyo;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

import yoyo.command.Command;
import yoyo.core.DialogHandler;
import yoyo.core.Parser;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.TaskList;

public class Yoyo  {
    private final Storage storage;
    private final DialogHandler dialogHandler;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    protected Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    protected Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Yoyo program.
     */
    public Yoyo() {
        dialogHandler = new DialogHandler();
        storage = new Storage("data/yoyo.txt");
        try {
            tasks = storage.load();
        } catch (YoyoException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage, dialogHandler);
        } catch (YoyoException e) {
            return e.getMessage();
        }

    }

}
