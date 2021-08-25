package duke;

public class Duke {
    private final Storage storage;
    private final TaskList list;
    private final Ui ui;


    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.list = new TaskList();
        this.ui = new Ui();
    }

    private void begin() {
        try {
            storage.readTasks(list);
        } catch (Exception e) {
            System.out.println("Could not read the data file: " + e.getMessage());
        }

        ui.welcome();
        String input = ui.getNextLine();
        Parser parser = new Parser(list);
        while (true) {
            if (input.equals("bye")) {
                break;
            }
            try {
                parser.parse(input);
                storage.writeTasks(list);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = ui.getNextLine();
        }
        ui.bye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.begin();
    }
}
