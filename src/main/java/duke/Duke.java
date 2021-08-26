package duke;

/**
 * Represents a robot object that can take in commands from the user and construct a todo list.
 * A file path is provided to a Duke object for the todo list to be saved locally on the user's
 * computer.
 */
public class Duke {

    private Command command;


    Duke(String filePath) {
        this.command = new Command(filePath);
    }

    /**
     * Initializes a Duke object and creates a file, "file.txt," that records the todo list of
     * the user when the program ends. The Duke object contains a Command object that takes commands from the user.
     *
     * @param args Not used in this program.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("file.text");
        duke.command.startDuke();
    }

}
