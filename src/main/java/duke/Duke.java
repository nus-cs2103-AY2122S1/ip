package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SaveCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.exception.IllegalCommandException;
import duke.gui.MainWindow;
import duke.parser.ParsedInput;
import duke.parser.Parser;
import duke.task.TaskList;

/**
 * Duke is a chatbot that responds to user input.
 *
 * @author Gabriel Goh
 */
public class Duke {

    private boolean hasQuit;
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final MainWindow mw;

    /**
     * Constructor without GUI reference for testing purposes.
     *
     * @param filePath Path to storage
     */
    public Duke(String filePath) {
        this.mw = null;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        load(filePath);
        assert tasks != null;
    }

    /**
     * Constructor to create Duke instance.
     *
     * @param filePath Path to storage
     * @param mw MainWindow instance
     */
    public Duke(String filePath, MainWindow mw) {
        this.mw = mw;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.mw.printMessage(load(filePath));
        assert tasks != null;
    }

    /**
     * Returns Ui of Duke instance.
     *
     * @return Ui instance
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Returns the size of the task list.
     *
     * @return size of task list.
     */
    public int taskSize() {
        return tasks.size();
    }

    /**
     * Returns if duke has stopped running.
     *
     * @return true if stopped
     */
    public boolean isQuit() {
        return hasQuit;
    }

    /**
     * Load tasks from file.
     *
     * @param filePath Load file location
     * @return Success message
     */
    public String load(String filePath) {
        if (storage.hasSave()) {
            try {
                tasks = storage.load();
                return ui.showLoadedMessage(filePath) + getResponse("");
            } catch (DukeException e) {
                tasks = new TaskList();
                return e.getMessage();
            }
        } else {
            tasks = new TaskList();
            return "I could not find your load file found at " + filePath + "!";
        }
    }


    /**
     * Gets response to user input.
     *
     * @param input String input
     * @return Response to display
     */
    public String getResponse(String input) {
        // Set default to List tasks
        if (input.equals("") || input.matches("[\\s+][\\n\\r]+")) {
            input = "LIST";
        }

        try {
            Parser parser = new Parser();
            ParsedInput parsedInput = parser.parse(input, this);
            Command.CommandTypes command = parsedInput.command;

            switch (command) {
            case BYE:
                hasQuit = true;
                return ui.showExitMessage();
            case LIST:
                return new ListCommand(tasks).run();
            case DONE:
                return new DoneCommand(parsedInput, tasks, ui).run();
            case DELETE:
                return new DeleteCommand(parsedInput, tasks, ui).run();
            case FIND:
                return new FindCommand(parsedInput, tasks).run();
            case TODO:
                return new TodoCommand(parsedInput, tasks, ui).run();
            case DEADLINE:
                return new DeadlineCommand(parsedInput, tasks, ui).run();
            case EVENT:
                return new EventCommand(parsedInput, tasks, ui).run();
            case SAVE:
                return new SaveCommand(storage, tasks, ui).run();
            case LOAD:
                return load(storage.toString());
            default:
                throw new IllegalCommandException(""); // should be unreachable by design
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
