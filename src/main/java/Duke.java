    public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.taskList = new TaskList(this.storage.load());

    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    public void run() {
        ui.printWelcomeMessage();

        boolean notExitCommand = true;

        while(notExitCommand) {
            try {
                String userCommand = ui.readCommand();
                Command command = Parser.parse(userCommand);
                notExitCommand = command.execute(this.taskList, this.ui, this.storage);
                this.storage.save(this.taskList.getformmatedData());

            } catch (InvalidInputException e) {
                ui.printError(e.getMessage());
            }          
        }

        ui.printExitMessage();
    }
}