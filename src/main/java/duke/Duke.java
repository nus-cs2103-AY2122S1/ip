package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Parser;
import duke.util.Ui;
import javafx.fxml.FXML;

public class Duke {
    private static final String FILEPATH = "data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    @FXML
    private Ui ui;

//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs duke.Duke objects
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e.toString());
            taskList = new TaskList();
        }
    }

    public String process(String input) {
        Command command = Parser.parseInput(taskList, input);
        return command.process();
    }

    /**
     * Runs program.
     */
    public void run() {
        ui.showGreeting();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            ui.readInput(taskList, input);
        }

        try {
            storage.saveData(taskList);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }

    }



//    public static void main(String[] args) {
//        new duke.Duke("data/duke.txt").run();
//    }


    Ui getUi() {
        return ui;
    }
//
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "duke.Duke heard: " + input;
    }
}
