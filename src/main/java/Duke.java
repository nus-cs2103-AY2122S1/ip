import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Duke {
    /**
     * Simple string array to store inputs
     **/
    private final DukeTaskList list;

    private final DukeDB database;

    private final DukeDateConfig config;

    private final Ui ui;
    /**
     * Basic constructor to initialise the list
     **/
    public Duke(DukeDB database, DukeDateConfig config) {
        this.ui = new Ui();
        this.database = database;
        this.list = new DukeTaskList(database.load().orElse(new ArrayList<>()));
        this.config = config;

    }


    public Duke(DukeDB database) {
        this.ui = new Ui();
        this.database = database;
        this.list = new DukeTaskList(database.load().orElse(new ArrayList<>()));
        this.config = DukeDateConfig.DDMMYYYY;
    }


    /**
     * Simple formatting tool to be used when printing commands
     *
     * @param text The text to be printed.
     */
    public static void printMsg(String text) {
        String bar = "===============================================";
        System.out.printf("%s\n%s\n%s\n", bar, text, bar);
    }

    /**
     * Method that listens and scans for user input.
     * Program will exit when the command "bye" is given to the scanner.
     */
    public void listen(){
        Scanner scanner = new Scanner(System.in);
        this.ui.greet(this.list.getSize());
        boolean terminate = true;
        while (terminate && scanner.hasNextLine()) {
            String scannedLine = scanner.nextLine();
            Optional<DukeCommands> prefix = DukeCommands.getCommand(scannedLine.split(" ")[0]);
            DukeCommands command = prefix.orElseGet(() -> DukeCommands.INVALID);
            try {
                terminate = command.action.run(Parser.parseCommand(scannedLine), this.list, this.database,
                        this.config, this.ui);
                this.database.save(this.list.getList());
            } catch(DukeException e) {
                ui.defaultPrint(e.toString());
            }
        }
    }


}

enum DukeDateConfig {
    DDMMYYYY("DDMMYYYY"),
    MMDDYYYY("MMDDYYYY");

    final String format;

    DukeDateConfig(String format) {
        this.format = format;
    }

    public static Optional<DukeDateConfig> getDukeDateConfig(String config) {
        return Arrays.stream(DukeDateConfig.values()).filter(x -> x.format.equals("config")).findFirst();
    }
}