package duke;

import duke.command.Command;
import duke.exceptions.UnknownException;
import duke.util.TaskList;
import duke.util.Parser;
import duke.util.Ui;
import duke.util.Storage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

import java.util.List;
import java.util.ArrayList;

public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    
    protected static final String FILE_URL = "data/Duke.txt";

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_URL);
        tasks = new TaskList(storage.loadStorage());
    }

    public static void main(String[] args) {
        new Duke().run();
    }
    
    /**
     * Returns an appropriate text reply for duke in the dialog box according
     * to user's input message.
     *      
     * @param input The user's input.
     * @return A text that duke should reply with.
     */
    public BotOutput getBotOutput (String input) {
        assert input != null : "User input should not be null";
        
        List<String> botOutputs = new ArrayList<>();
        boolean isExit = false;

        try {
            Command c = Parser.parse(input);
            botOutputs.addAll(c.execute(tasks, ui, storage));
            isExit = c.isExit();
        } catch (UnknownException e) {
            ui.displayError(e.getMessage());
        }

        return new BotOutput(String.join("\n", botOutputs), isExit);
    }

    public String greetToGui() {
        return ui.greet();
    }

    /**
     * Runs the program with the correct components and storage file set-ups.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readNextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (UnknownException e) {
                ui.displayError(e.getMessage());
            }
        }
    }
}

