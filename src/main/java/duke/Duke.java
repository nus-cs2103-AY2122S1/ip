package duke;

import duke.command.DukeCommand;
import duke.util.DukeConfig;
import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeTaskList;
import duke.util.Parser;
import duke.util.Ui;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Duke {
    /** Simple string array to store inputs **/
    private final DukeTaskList list;
    private final DukeDB database;
    private final DukeConfig config;
    private final Ui ui;

    /**
     * Basic constructor to initialise the list
     **/
    public Duke(DukeDB database, DukeConfig config) {
        this.ui = new Ui();
        this.database = database;
        this.list = new DukeTaskList(database.load()
                .orElse(new ArrayList<>()));
        this.config = config;
    }


    public Duke(DukeDB database) {
        this.ui = new Ui();
        this.database = database;
        this.list = new DukeTaskList(database.load()
                .orElse(new ArrayList<>()));
        this.config = new DukeConfig();
    }


    /**
     * Formats print commands with borders.
     *
     * @param text The text to be printed.
     */
    public static void printMsg(String text) {
        String bar = "===============================================";
        System.out.printf("%s\n%s\n%s\n",
                bar,
                text,
                bar);
    }

    /**
     * Method that listens and scans for user input.
     * Program will exit when the command "bye" is given to the scanner.
     */
    public void listen() {
        Scanner scanner = new Scanner(System.in);
        this.ui.greet(this.list.getSize());
        boolean shouldTerminate = true;
        while (shouldTerminate && scanner.hasNextLine()) {
            String scannedLine = scanner.nextLine();
            Optional<DukeCommand> prefix = DukeCommand.getCommand(scannedLine.split(" ")[0]);
            DukeCommand command = prefix.orElseGet(() -> DukeCommand.INVALID);
            try {
                shouldTerminate = command.action.runAndCanContinue(Parser.parseCommand(scannedLine),
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


}