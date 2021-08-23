public class Duke {
    private final String name;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    public Duke(String name) {
        this.name = name;
        this.ui = new Ui();
        Storage storage = new Storage("list.txt");
        this.taskList = new TaskList(storage.load());
        this.parser = new Parser(this.taskList);
    }

    void initialize() {
        greet();
        listen();
    }

    void greet() {
        ui.printDivider();
        ui.print("Hey there! I'm %s%n", name);
        ui.print("How can I help you?");
        ui.printDivider();
    }

    void listen() {
        String input;
        boolean isListening;

        do {
            input = ui.readFromUser();
            isListening = parser.parseInput(input);
        } while (isListening);
    }

    public static void main(String[] args) {
        new Duke("HAL9000").initialize();
    }
}
