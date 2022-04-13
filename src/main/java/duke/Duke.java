package duke;
/**
 * Main class. Contains the duke program and its data.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;


    Duke(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
        parser.addTaskList(tasks);
        parser.addStorage(storage);
        assert tasks != null;
        assert storage != null;
    }


    public String getResponse(String input) {
        return parser.parse(input);
    }

}
