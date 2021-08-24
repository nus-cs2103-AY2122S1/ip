import jarvis.command.Command;
import jarvis.exception.JarvisException;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

public class Jarvis {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage;
        TaskList taskList;

        try {
            storage = new Storage("./data/jarvis.txt");
            taskList = new TaskList(storage.loadTasksFromFile());
        } catch (JarvisException e) {
            ui.showError(e);
            return;
        }

        ui.showGreetingMessage();

        String userInput = ui.readInput();

        while (true) {
            try {
                if (Parser.isUserInputEmpty(userInput)) {
                    userInput = ui.readInput();
                    continue;
                }
                if (Parser.shouldExit(userInput)) {
                    break;
                }
                Command command = Command.createCommand(userInput);
                command.execute(taskList, storage, ui);
            } catch (JarvisException e) {
                ui.showError(e);
            }
            userInput = ui.readInput();
        }

        ui.showExitMessage();
        ui.closeScanner();
    }
}
