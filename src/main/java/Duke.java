public class Duke {

    private Storage storage;  // deals with loading tasks from the file and saving tasks in the file
    private TaskList tasks;  // contains the task list: has operations to add/delete tasks in the list
    private Ui ui;          // deals with interactions with the user

    Duke(String filePath)  {
        this.ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (InvalidStorageFilePathException isfpe) {
            ui.showError(isfpe.getMessage());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidDirectoryException ide) {
                ui.showError(ide.getMessage());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasklist.txt").run();
    }

}
