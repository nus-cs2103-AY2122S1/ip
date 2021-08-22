import java.io.IOException;
import util.commons.Messages;
import util.tasks.*;
import java.time.format.DateTimeParseException;
import util.commands.*;
import util.parser.*;
import util.storage.*;
import util.ui.*;

public class Duke {

    private final Parser parser;
    private final Ui ui = new Ui();
    private final Storage stg;
    private final TaskList tasks;
    private static final String saveDirectory = "data/";
    private static final String saveFilePath = "save.txt";
    private static final String tempFilePath = "temp.txt";
    private final DateTaskTable dateTaskTable;







    public Duke(String filename, String tempfilepath) {
        this.tasks = new TaskList();
        this.dateTaskTable = new DateTaskTable();
        this.parser = new Parser(this.ui, this.tasks, this.dateTaskTable);
        this.stg = new Storage(saveDirectory + filename
                , saveDirectory + tempfilepath
                , this.dateTaskTable);
        try {
            this.tasks.addAll(this.stg.read());
        } catch (IOException | DukeException ex) {
            ui.print(ex.getMessage());
        }
        ui.print_logo();
    }







    /**
     * Running Duke.
     */
    public void run() {

        //initialising Duke
        //via greetings
        ui.print_logo();
        ui.print(Messages.GREETINGS);

        while(!ExitCommand.isExit) {
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



    public static void main(String[] args) {
        Duke d = new Duke(saveFilePath, tempFilePath);
        d.run();



    }
}
