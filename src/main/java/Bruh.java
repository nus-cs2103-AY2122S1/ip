public class Bruh {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Bruh(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = storage.loadTaskList();
    }

    /**
     * Begin listening for user input. Terminates when the user says 'bye'.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readUserInput();
                Command command = Parser.parseInputToCommand(userInput);
                command.execute(taskList, ui, storage);
                ui.showCommandDescription(command);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showErrorMessage("Please specify a valid task number (use 'list' to view your tasks).");
            }
        }
        exit();
    }

    private void exit() {
        storage.saveToDisk(taskList);
    }

    public static void main(String[] args) {
        final String STORAGE_PATH = "output/tasklist.txt";
        new Bruh(STORAGE_PATH).run();
    }
}
