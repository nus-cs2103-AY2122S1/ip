import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    public Duke()  {
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public void run() throws IOException {
        ui.welcome();
        boolean isExist = true;

        while(isExist) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExist = c.isExist();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Duke("./data/tasks.txt").run();
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }
}
