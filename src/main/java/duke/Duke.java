package duke;

import java.util.ArrayList;
import java.util.Optional;

import duke.command.DukeCommand;
import duke.util.DukeConfig;
import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeExitException;
import duke.util.DukeTaskList;
import duke.util.Parser;
import duke.util.Ui;


/**
 * Class for Duke. Holds all the class variables and methods.
 */
public class Duke {
    /**
     * Simple string array to store inputs
     **/
    private final DukeTaskList list;
    private final DukeDB database;
    private final DukeConfig config;
    private final Ui ui;

    /**
     * Basic constructor to initialise the list
     **/
    public Duke(DukeDB database, DukeConfig config) {
        super();
        this.ui = new Ui();
        this.database = database;
        this.list = new DukeTaskList(database.load()
                .orElse(new ArrayList<>()));
        this.config = config;
    }


    /**
     * Constructor for creating duke with an input Database
     * @param database The dukeDB to write to.
     */
    public Duke(DukeDB database) {
        super();
        this.ui = new Ui();
        this.database = database;
        this.list = new DukeTaskList(database.load()
                .orElse(new ArrayList<>()));
        this.config = new DukeConfig();
    }

    /**
     * Public constructor to init duke without a DB.
     */
    public Duke() {
        this.ui = new Ui();
        this.database = new DukeDB();
        this.list = new DukeTaskList(database.load()
                .orElse(new ArrayList<>()));
        this.config = new DukeConfig();
    }


    /**
     * Formats print commands with borders.
     *
     * @param text The text to be printed.
     */
    public static String printMsg(String text) {
        String bar = "===============================================";
        String output = String.format("%s\n%s\n%s\n",
                bar,
                text,
                bar);
        System.out.println(output);
        return output;
    }


    /**
     * Takes in an input from the user and returns an output
     * @param input Optional string output. If empty, indicates that the user used the Bye command
     * @return Optional String containing the output
     */
    public Optional<String> takeInput(String input) {
        Optional<DukeCommand> prefix = DukeCommand.getCommand(input.split(" ")[0]);
        DukeCommand command = prefix.orElseGet(() -> DukeCommand.INVALID);
        try {
            Optional<String> output = command.action.run(Parser.parseCommand(input),
                    this.list,
                    this.database,
                    this.config,
                    this.ui);
            this.database.save(this.list.getList());
            return output;
        } catch (DukeExitException exitException) {
            return Optional.empty();
        } catch (DukeException e) {
            return Optional.of(e.toString());
        }
    }
}
