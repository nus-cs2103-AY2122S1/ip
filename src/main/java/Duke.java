import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Duke {

    // The Global Variables used by the ChatBot
    private static final Scanner cmdReader = new Scanner(System.in);
    public final UI ui;
    private Storage storage;
    private final Parser parser;
    private TaskList taskList;

    public Duke() {
        this.parser = new Parser();
        this.ui = new UI();
        try {
            this.storage = new Storage("." + File.separator + "data" + File.separator + "WhoBotData.txt");
            this.taskList = new TaskList(storage);
        } catch (DukeException ex) {
            ui.echo(ex.getMessage(), UI.TYPE.ERROR);
            System.exit(0);
        }
    }


    public void run() {
        ui.greeting();
        while (true) {
            try {
                String command;
                System.out.print(UI.COLOR_PURPLE + "> " + UI.COLOR_RESET);
                command = cmdReader.nextLine().trim();
                if (parser.parse(command, ui, storage, taskList) == -1) {
                    break;
                };
            } catch (DukeException ex) {
                ui.echo(ex.getMessage(), UI.TYPE.ERROR);
            }
        }
    }

    //Main Method
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
