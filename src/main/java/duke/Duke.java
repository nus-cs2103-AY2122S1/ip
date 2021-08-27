package duke;

import java.io.IOException;

public  class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            System.out.println("Past records (if any):");
            taskList = storage.loadData();
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        Parser parser = new Parser(this.taskList, this.ui, this.storage);
        parser.parse();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
