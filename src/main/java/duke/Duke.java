package duke;

/**
 * Runs the initialization process for Duke chatbot for IP of CS2103 2021.
 */
public class Duke {

    private final UI userInterface;
    private final Parser parser;

    /**
     * Initializes the necessary components for Duke to function.
     */
    public Duke() {
        parser = new Parser();
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        userInterface = new UI(parser, storage, taskList);
    }

    /**
     * Runs Duke logic through parser to get string response from UI.
     *
     * @param input String command by user.
     * @return Duke response to be sent to GUI components to be generated.
     */
    public String getResponse(String input) {
        CommandType command = this.parser.nextCommand(input);
        try {
            return this.userInterface.start(command);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
