package duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage() + "\nStarting Duke without saved tasks...");
            tasks = new TaskList();
        }
    }

    private void start() {
        ui.hello();
        String userInput = ui.getUserInput();
        Parser parser = new Parser(tasks);
        while (!userInput.equals("bye")) {
            try {
                ui.printMessage(parser.parse(userInput));
                storage.save(tasks);
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            }
            userInput = ui.getUserInput();
        }
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").start();
    }

}
