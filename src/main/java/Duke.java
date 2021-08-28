public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    
    public Duke(String filePath) {
        taskList = new TaskList();
        storage = new Storage(filePath);
        ui = new Ui(taskList, storage);
    }
    
    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.command();
            Parser parser = new Parser(command);
            if (parser.isExit()) {
                break;
            }
            Command c = parser.parse(command);
            c.execute(taskList, storage);
            isExit = parser.isExit();
        }
    }
    
    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}