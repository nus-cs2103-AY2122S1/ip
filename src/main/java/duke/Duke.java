package duke;

import javafx.scene.image.Image;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {

    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Class constructor.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Runs the Duke Personal Assistant Chatbot.
     */
    public void run() {
        boolean terminate = false;
        ui.printMessage(this.onStart());

        while (!terminate) {
            String message;
            try {
                String input = ui.readInput();
                message = parser.executeCommand(ui, tasks, input);
                terminate = parser.getToTerminate();
            } catch (Exception e) {
                message = ui.handleException(e);
            }
            ui.printMessage(message);
        }
    }

    public String onStart() {
        return ui.welcome();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            return parser.executeCommand(ui, tasks, input);
        } catch (Exception e) {
            return ui.handleException(e);
        }
    }
}
