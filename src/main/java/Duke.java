import java.util.*;

public class Duke {
    private static final String SAVE_FILE_LOCATION = "duke-task-list.txt";
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

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
            shouldListen = processCommand(command);
        } while (shouldListen);
        ui.printExitMessage();
    }

    /**
     * Processes the given command (a line). Returns true if more commands are to be listened to.
     *
     * @param command The command to be processed.
     * @return If Duke should continue listening to commands.
     */
    public boolean processCommand(String command) {
        // Get the longest duke command that matches to command
        Optional<DukeCommand> dukeCommand = DukeCommand.getClosestMatch(command);
        if (dukeCommand.isPresent()) {
            DukeCommand actualCommand = dukeCommand.get();
            String arguments = command.substring(actualCommand.getName().length());
            String[] tokens = arguments.split("/");
            String positionalArg = tokens[0].trim();
            Map<String, String> namedArgs = new HashMap<>();
            for (int i = 1; i < tokens.length; i++) {
                String[] namedArg = tokens[i].trim().split(" ", 2);
                namedArgs.put(namedArg[0].trim(), namedArg[1].trim());
            }
            try {
                return dukeCommand.get().apply(taskList, ui, storage, positionalArg, namedArgs);
            } catch (InvalidCommandException e) {
                ui.outputLine(String.format("Error in \"%s\": %s\nType \"help %s\" to view proper usage of the command.", actualCommand.getName(), e.getMessage(), actualCommand.getName()));
                return true;
            }
        } else {
            ui.outputLine(String.format("Unknown command: %s. Type \"help\" for a list of available commands.", command));
            return true;
        }
    }
}
