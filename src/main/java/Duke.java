public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadData());
        this.parser = new Parser(storage, taskList);
        this.ui = new Ui();
    }

    public void run() {
        ui.sayGreeting();
       while (!this.parser.isTerminated()) {
            ui.printMessage(parser.parseCommand(ui.getUserInput()));
       }

    }

    public static void main(String[] args) {
            new Duke("src/main/java/data.txt").run();
        }

}


