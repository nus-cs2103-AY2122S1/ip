import java.io.IOException;
import java.time.format.DateTimeParseException;

import util.commands.CommandList;
import util.commands.ExitCommand;
import util.commons.Messages;
import util.parser.Parser;
import util.storage.Storage;
import util.tasks.DateTaskTable;
import util.tasks.DukeException;
import util.tasks.TaskList;
import util.ui.Ui;


/**
 * The class representing the Duke.
 *
 *
 */
public class Duke {
    private static final String saveDirectory = "data/";
    private static final String saveFilePath = "save.txt";
    private static final String tempFilePath = "temp.txt";

    private final Parser parser;
    private final Ui ui = new Ui();
    private final Storage stg;
    private final TaskList tasks;
    private final DateTaskTable dateTaskTable;


    /**
     * Constructor for duke.
     *
     * @param filename The file to save at.
     * @param tempFilePath The tempfile name to use.
     */
    public Duke(String filename, String tempFilePath) {
        this.tasks = new TaskList();
        this.dateTaskTable = new DateTaskTable();
        this.parser = new Parser(this.ui, this.tasks, this.dateTaskTable);
        this.stg = new Storage(saveDirectory + filename,
                saveDirectory + tempFilePath,
                this.dateTaskTable);
        try {
            this.tasks.addAll(this.stg.read());
        } catch (IOException | DukeException ex) {
            ui.print(ex.getMessage());
        }
        ui.print_logo();
    }




    public String getResponse(String inpt) {
        try {
            CommandList cmds = parser.inputsParser(inpt);
            String result = cmds.executeAll();
            this.stg.write(this.tasks);
            return result;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Expected date format YYYY MM DD";
        } catch (IOException e) {
            return e.getMessage();
        }

    }


    /**
     * Running Duke.
     */
    public void run() {

        //initialising Duke
        //via greetings
        ui.print(Messages.GREETINGS);

        while (!ExitCommand.isExit()) {
            String inpt = ui.getInput();
            try {
                CommandList cmds = parser.inputsParser(inpt);
                cmds.executeAll();
                stg.write(this.tasks);
            } catch (DukeException e) {
                ui.print_error_message(e);
            } catch (DateTimeParseException e) {
                ui.print("Expected date format YYYY MM DD");
            } catch (IOException e) {
                ui.print_error_message(e);
            }
        }
        ui.print(Messages.BYE);

    }


    /**
     * The main function of Duke.
     *
     * @param args The arguments for duke.
     */
    public static void main(String[] args) {
        Duke d = new Duke(saveFilePath, tempFilePath);
        d.run();



    }
}
