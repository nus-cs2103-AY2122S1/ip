package duke;

public class Duke {
    private static String userDir = System.getProperty("user.dir");
    private DukeTaskList dukeTaskList;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        dukeTaskList = new DukeTaskList();
        storage = new Storage(userDir, dukeTaskList);
        parser = new Parser(dukeTaskList, storage);
        storage.loadDataFile();
    }

    /**
     * Returns Duke's response.
     *
     * @param input User input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }
}



