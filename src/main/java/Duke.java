import duke.Parser;
import duke.Storage;
import duke.TaskList;

public class Duke {
    private final Parser parser;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        String filePath = "dukeData.txt";
        Storage storage = new Storage(filePath);
        TaskList tasks;
        tasks = new TaskList(storage.loadData());
        parser = new Parser(storage, tasks);
    }

    /**
     * Gets the response of duke depending on input.
     *
     * @param input User input.
     * @return Response to be printed.
     */
    public String getResponse(String input) {
        return parser.command(input);
    }
}
