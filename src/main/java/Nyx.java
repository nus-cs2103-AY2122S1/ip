public class Nyx {
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

    public Nyx(String folderName, String fileName) {
        ui = new Ui();
        this.storage = new Storage(folderName, fileName);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (NyxException e) {
            ui.displayOutput(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.displayStart();

        String input;

        do {
            input = ui.readInput();
            try {
                String output = Parser.parse(input, taskList, storage);
                ui.displayOutput(output);
            } catch (NyxException e) {
                ui.displayOutput(e.getMessage());
            }

        } while (!input.equals("bye"));
    }

    public static void main(String[] args) {
        new Nyx("data", "nyx.txt").run();
    }
}