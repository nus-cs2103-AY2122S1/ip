package duke;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Class which operates Jarvis the chat-bot
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private final String NO_TASKS = "There are currently no tasks on your list :)";
    private final String UNRECOGNISED_COMMAND = "I'm sorry, but I don't know what that means :(";
    private final String BYE = "Bye! Hope to see you soon :)";

    /**
     * Retrieves all the tasks stored by Jarvis in the hard disk upon running the main method.
     *
     * @param filePath The file in which the tasks are stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            storage.retrieveFileContents();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    /**
     * Returns the response to be given by Jarvis to the user upon receiving a command
     *
     * @param input the command given by the user
     */
    public String getResponse(String input) {
            try {
                switch (Parser.parseCommand(input)) {
                    case "list":
                        // If there are no tasks in the task list
                        if (TaskList.getCounter() == 0) {
                            return "\t" + NO_TASKS;
                        } else {
                            return Parser.parseList();
                        }
                    case "done":
                        return Parser.parseDone(input);
                    case "delete":
                        return Parser.parseDelete(input);
                    case "todo":
                        return Parser.parseTodo(input);
                    case "deadline":
                        return Parser.parseDeadline(input);
                    case "event":
                        return Parser.parseEvent(input);
                    case "today":
                        return Parser.parseToday();
                    case "find":
                        return Parser.parseFind(input);
                    case "bye":
                        return BYE;
                    default:
                        throw new DukeException(UNRECOGNISED_COMMAND);
                }
            } catch (DukeException | IOException e) {
                return "" + e;
            }
    }
}


