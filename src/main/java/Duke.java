import javax.swing.plaf.synth.SynthDesktopIconUI;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.xml.stream.FactoryConfigurationError;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;


/**
 * Represents the duke chat bot, which has the ability to log and track and delete tasks.
 * 
 * @author Gordon Yit
 * @Since 23-08-21
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    /**
     * Constructor for the Duke class.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    /**
     * Executes the duke chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    
    /**
     * Runs the Duke chat bot.
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("../data/duke.txt").run();
    }
}
