import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.command.Command;
import duke.command.CommandResult;
import duke.tasks.TaskManager;
import duke.tasks.TaskManagerUi;
import javafx.application.Application;

import java.util.Scanner;

public class Duke {

    // Path to file data/duke.txt
    public static final String DUKE_TXT = "data/duke.txt";

    private TaskManager taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private Scanner in;

    public Duke() {
        this(DUKE_TXT);
    }

    public Duke(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();
        TaskManagerUi result = storage.initialiseTaskList();
        taskList = result.taskList;
    }

    public CommandResult executeCommand(Command command) {
        try {
            return command.execute();
        } catch (Exception e) {
            return new CommandResult(e.getMessage(), false, null);
        }
    }

    CommandResult getResponse(String data) {
        Command command = parser.parseCommand(taskList, data);
        CommandResult commandResult = executeCommand(command);
        if (commandResult.isUpdated()) {
            taskList = commandResult.getTaskList();
            String errorMessage = storage.writeToFile(taskList);
        }
        return commandResult;
    }


    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}