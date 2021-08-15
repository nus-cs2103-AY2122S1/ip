import java.util.*;

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
        try {
            DukeCommand.HELP.apply(taskList, ui, storage, "", Map.of());
        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }
        boolean shouldListen;
        do {
            String command = ui.nextCommand();
            shouldListen = parseAndRun(command);
        } while (shouldListen);
        ui.printExitMessage();
    }

    public boolean parseAndRun(String commandStr) {
        DukeCommandWithArgs command = parser.parse(commandStr);
        if (command == null) {
            // Command not found
            ui.outputLine(String.format("Unknown command: %s. Type \"help\" for a list of available commands.", commandStr));
            return true;
        }
        try {
            return command.runWith(taskList, ui, storage);
        } catch (InvalidCommandException e) {
            ui.outputLine(String.format("Error in \"%s\": %s\nType \"help %s\" to view proper usage of the command.", command.getBaseCommand().getName(), e.getMessage(), command.getBaseCommand().getName()));
            return true;
        }
    }
}
