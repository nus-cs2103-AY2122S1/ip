import duke.Parser;
import duke.Storage;
import duke.TaskList;

public class Duke{
    private final Parser parser;

    public Duke() {
        String filePath = "dukeData.txt";
        Storage storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadData());
        } catch (Exception e) {
            System.out.println(e + "hello");
            tasks = new TaskList();
        }
        parser = new Parser(storage, tasks);
    }

    public String getResponse(String input) {
        return parser.command(input);
    }
}
