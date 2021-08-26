import java.io.IOException;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.ExitCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void start() {
        ui = new Ui();
        parser = new Parser();
        try {
            taskList = new TaskList(Storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
        ui.showWelcome();
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            ui.showHorizontalLine();
            command = parser.parse(userCommandText);
            command.setTaskList(taskList);
            CommandResult result = command.execute();
            ui.showResultToUser(result);
            ui.showHorizontalLine();
            ui.showBlankLine();
        } while (!(command instanceof ExitCommand));
    }

    private void exit() {
        try {
            Storage.save(taskList.formatData());
        } catch (IOException e) {
            ui.showSavingError(e.getMessage());
        }
        ui.showGoodbye();
        ui.close();
        System.exit(0);
    }
}