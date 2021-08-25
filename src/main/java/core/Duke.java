package core;

import commands.Command;
import gui.Ui;

public class Duke {
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = storage.loadStorageToTaskList();
    }

    private void run() {
        boolean shouldExit = false;
        Ui.greet();
        while (!shouldExit) {
            String input = Ui.readInput();
            Command command = Parser.parse(input, taskList);
            command.execute(taskList, storage);
            shouldExit = command.shouldExit();
        }
    }

    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
    }
}
