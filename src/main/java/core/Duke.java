package core;

import commands.Command;
import gui.Ui;

public class Duke {
    private TaskList taskList;

    public Duke() {
        taskList = new TaskList();
    }

    private void run() {
        boolean shouldExit = false;

        Ui.greet();
        while (!shouldExit) {
            String input = Ui.readInput();
            Command command = Parser.parse(input);
            command.execute(taskList);
            shouldExit = command.shouldExit();
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
