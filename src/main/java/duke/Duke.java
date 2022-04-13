package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.GetDayCommand;
import duke.command.ListCommand;
import duke.command.SearchCommand;

/**
 * This class is the Duke class to start the whole program
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private final TaskList myTasks;
    private final Parser parser;
    private MainWindow mainWindow;

    /**
     * Constructs a new duke.Duke instance
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        myTasks = new TaskList();
        parser = new Parser();
        run();
    }

    private void run() {
        storage.loadFile();
    }

    /**
     * Decides which instruction is given from user.
     *
     * @param input input from user
     */
    public void processInput(String input) {
        Parser.KeyWord result = parser.parse(input);
        Command executeNext;
        switch (result) {
        case END:
            ExitCommand exitCommand = new ExitCommand();
            exitCommand.execute();
            break;
        case LIST:
            executeNext = new ListCommand(myTasks);
            executeNext.execute();
            break;
        case DONE:
            executeNext = new DoneCommand(myTasks, input);
            executeNext.execute();
            break;
        case DELETE:
            executeNext = new DeleteCommand(myTasks, input);
            executeNext.execute();
            break;
        case GET:
            executeNext = new GetDayCommand(input);
            executeNext.execute();
            break;
        case SEARCH:
            executeNext = new SearchCommand(myTasks, input);
            executeNext.execute();
            break;
        case ADD:
            executeNext = new AddCommand(myTasks, input);
            executeNext.execute();
            break;
        default:
            break;
        }
    }

    /**
     * Saves file
     */
    public static void saveFile() {
        Storage.saveFile();
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        ui.setMainWindow(mainWindow);
    }
}
