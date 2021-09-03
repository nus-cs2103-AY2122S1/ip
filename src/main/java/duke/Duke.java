package duke;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("./Duke.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }
//
//    /**
//     * Runs the chat-bot
//     */
//    public void run() {
//        ui.showWelcomeMessage();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readNextLine();
//                Command c = Parser.parse(fullCommand);
//                c.execute(taskList, storage, ui);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            }
//        }
//        ui.showEndMessage();
//    }
//
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
