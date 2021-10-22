package duke;

import duke.command.DukeCommandWithArgs;
import duke.exception.DukeStorageException;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Duke extends Application {
    private static final String SAVE_FILE_LOCATION = "duke-task-list.txt";

    private final boolean useGui;
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;

    /**
     * Duke constructor
     */
    public Duke() {
        this(true);
    }

    /**
     * Duke constructor
     * @param useGui Whether to use GUI. Otherwise, CLI mode is used
     */
    public Duke(boolean useGui) {
        this.useGui = useGui;
        this.storage = new Storage(SAVE_FILE_LOCATION);
        if (useGui) {
            this.ui = new Gui(storage);
        } else {
            this.ui = new Cli(System.in, System.out);
        }
        TaskList taskList;
        try {
            taskList = storage.loadTaskList();
        } catch (DukeStorageException e) {
            if (ui instanceof Cli) {
                Cli cli = (Cli) ui;
                cli.outputLine(e.getMessage());
            }
            taskList = new TaskList();
        }
        this.taskList = taskList;
        this.parser = new Parser();
    }

    @Override
    public void start(Stage primaryStage) {
        assert useGui;
        Gui gui = (Gui) ui;
        Scene scene = new Scene(gui.getRootNode());
        primaryStage.setTitle("Duke");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Starts Duke CLI.
     */
    public void startCli() {
        assert !useGui;
        Cli cli = (Cli) ui;
        cli.printWelcomeMessage();
        cli.printHelp();
        while (cli.shouldContinue()) {
            String commandStr = cli.nextCommand();
            DukeCommandWithArgs command = parser.parse(commandStr);

            if (command == null) {
                // Command not found
                cli.outputLine(String.format("Unknown command: %s. Type \"help\" for a list of available commands.",
                        commandStr));
                continue;
            }

            try {
                command.runWith(taskList, cli, storage);
            } catch (InvalidCommandException e) {
                cli.outputLine(
                        String.format("Error in \"%s\": %s\nType \"help %s\" to view proper usage of the command.",
                                command.getBaseCommand().getName(), e.getMessage(),
                                command.getBaseCommand().getName()));
            }
        }
        cli.printExitMessage();
    }
}
