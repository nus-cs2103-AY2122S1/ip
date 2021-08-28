package duke;

/**
 * The Duke class that runs the Duke program.
 */
public class Duke {
    private static TaskList list;
    private static FileManager fm;
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        fm = new FileManager();
        try {
            list = fm.getListFromFile();
        } catch (DukeException e) {
            ui.showError(e);
            list = new TaskList();
        }
    }

    private static void addTask(String[] args) throws DukeException {
        switch (args[0]) {
        case "todo":
            list.add(new Todo(args[1]));
            break;
        case "deadline":
            list.add(new Deadline(args[1], args[2]));
            break;
        case "event":
            list.add(new Event(args[1], args[2]));
            break;
        default:
            break;
        }
    }

    /**
     * A method that runs the program.
     */
    public void run() {
        ui.greet();
        while (true) {
            String input = ui.getInput();
            Parser parser = new Parser(input);
            try {
                String[] args = parser.parse();
                String cmd = args[0];
                if (cmd.equals("bye")) {
                    fm.writeToFile(list);
                    ui.stopInput();
                    break;
                } else if (cmd.equals("list")) {
                    ui.displayList(list);
                } else if (cmd.equals("done") || cmd.equals("delete")) {
                    list.editTask(args);
                } else if (cmd.equals("find")) {
                    TaskList matches = list.find(args[1]);
                    ui.displayMatching(matches);
                } else {
                    addTask(args);
                }
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
