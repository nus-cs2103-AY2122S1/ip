/**
 * The Duke program implements a bot with a set of simple commands
 *
 * @author Calvin Tan
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }
   
    // Need to go through program flow for when no file in the beginning.
    public void run() {
       ui.welcome();
       boolean isExit = false;
       while (!isExit) {
           try {
               String fullCommand = ui.readCommand();
               ui.printBorder();
               Command c = Parser.parse(fullCommand);
               c.execute(tasks, ui, storage);
               isExit = c.isExit();
           } catch (DukeException e) {
               ui.showError(e.getMessage());
           } finally {
               ui.printBorder();
           }
       }
       ui.end();
       
       try {
           storage.write(tasks);
       } catch (DukeException e) {
           ui.showError(e.getMessage());
       }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
