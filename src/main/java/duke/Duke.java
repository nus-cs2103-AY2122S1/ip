package duke;

import duke.command.DukeCommand;
import duke.util.DukeConfig;
import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeExitException;
import duke.util.DukeTaskList;
import duke.util.Parser;
import duke.util.Ui;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

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


    public Duke(DukeDB database) {
        super();
        this.ui = new Ui();
        this.database = database;
        this.list = new DukeTaskList(database.load()
                .orElse(new ArrayList<>()));
        this.config = new DukeConfig();
    }

    public Duke() {
        this.ui = new Ui();
        this.database = new DukeDB(null);
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

    @Deprecated
    public void listen() {
        Scanner scanner = new Scanner(System.in);
        this.ui.greet(this.list.getSize());
        boolean shouldTerminate = true;
        while (scanner.hasNextLine()) {
            String scannedLine = scanner.nextLine();
            Optional<DukeCommand> prefix = DukeCommand.getCommand(scannedLine.split(" ")[0]);
            DukeCommand command = prefix.orElseGet(() -> DukeCommand.INVALID);
            try {
                command.action.run(Parser.parseCommand(scannedLine),
                        this.list,
                        this.database,
                        this.config,
                        this.ui);
                this.database.save(this.list.getList());
            } catch (DukeException e) {
                this.ui.defaultPrint(e.toString());
            }
        }
    }

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
        } catch (DukeExitException exitException){
            return Optional.empty();
        } catch(DukeException e){
            return Optional.of(e.toString());
        }
    }
}