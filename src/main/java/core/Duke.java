package core;

import commands.Command;
import gui.Ui;

public class Duke {
    private Ui ui;
    private TaskList taskList;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
    }

    private void run() {
        boolean shouldExit = false;

        ui.greet();
        while (!shouldExit) {
            String input = ui.readInput();
            Command command = new Command(input);
            command.execute(taskList);
            shouldExit = command.shouldExit();
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
