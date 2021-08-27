package duke;

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = storage.loadTask();
        ui = new Ui(storage, taskList);
    }

    public void run() {
        boolean isExit = false;
        ui.greet();
        taskList.printTask();
        while (!isExit) {
            String input = ui.readLine();
            ui.handleInput(input);
            ui.showLine();
            isExit = ui.handleExit();
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}