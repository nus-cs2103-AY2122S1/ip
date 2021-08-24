package duke;

import duke.command.DukeCommandWithArgs;
import duke.exception.DukeStorageException;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;

public class Duke {
    private static final String SAVE_FILE_LOCATION = "duke-task-list.txt";
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    public Duke() {
        this.ui = new Ui(System.in, System.out);
        this.storage = new Storage(SAVE_FILE_LOCATION);
        TaskList taskList;
        try {
            taskList = storage.loadTaskList();
        } catch (DukeStorageException e) {
            ui.outputLine(e.getMessage());
            taskList = new TaskList();
        }
        this.taskList = taskList;
        this.parser = new Parser();
    }

    public void start() {
        ui.printWelcomeMessage();
        ui.printHelp();
        while (ui.shouldContinue()) {
            String commandStr = ui.nextCommand();
            DukeCommandWithArgs command = parser.parse(commandStr);
            if (command == null) {
                // Command not found
                ui.outputLine(String.format("Unknown command: %s. Type \"help\" for a list of available commands.",
                        commandStr));
            } else {
                try {
                    command.runWith(taskList, ui, storage);
                } catch (InvalidCommandException e) {
                    ui.outputLine(
                            String.format("Error in \"%s\": %s\nType \"help %s\" to view proper usage of the command.",
                                    command.getBaseCommand().getName(), e.getMessage(),
                                    command.getBaseCommand().getName()));
                }
            }
        }
        ui.printExitMessage();
    }
}
