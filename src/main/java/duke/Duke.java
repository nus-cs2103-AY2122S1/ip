package duke;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

public class Duke {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    
    /**
     * Initialize each component of the program.
     * Loads any data available from filePath.
     *
     * @param filePath path to the storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.printMessage(e.getMessage());
            taskList = new TaskList();
        }
    }
    
    /**
     * Run the program.
     */
    public void run() {
        String input;
        
        // Program exits only if user inputs "bye"
        while (!(input = ui.readCommand()).equals("bye")) {
            try {
                // Interpret and execute the command input by user
                String message = Parser.interpretCommand(input).execute(taskList);
                ui.printMessage(message);
                
                // Update storage file
                storage.save(taskList.getTasks());
            } catch (DukeException | IOException e) {
                ui.printMessage(e.getMessage());
            }
        }
        
        // Exit Ui
        ui.exit();
    }
    
    /**
     * Initialize and start the program.
     *
     * @param args any CLI input but currently not used in program
     */
    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
