import java.util.Scanner;

public class Duke {

    private PersistentStorage storage;
    private Tasklist taskList;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI(new Scanner(System.in));
        storage = new PersistentStorage(filePath);
        try {
            taskList = storage.loadTasks();
        } catch (DukeException e) {
            ui.showLoadError();
            taskList = new Tasklist();
        }
    }

    public void run() {
        ui.showStartMsg();
        Boolean exit = false;

        while (!exit) {
            try {
                String rawCommand = ui.readCommand();
                Command command = Parser.parse(rawCommand);
                command.executeCommand(taskList, ui, storage);
                exit = command.isExit();

            } catch (DukeException e) {
                ui.showErrorMsg(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/taskdata.txt");
        duke.run();
    }
}
