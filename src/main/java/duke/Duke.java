package duke;

import command.Command;
import exception.DukeException;
import exception.DukeExceptionType;

import java.io.InputStream;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    
    private final Ui ui;
    private final TaskList taskList;
    
    public Duke(String fileDirectory, String fileName) {
        ui = new Ui();
        Storage storage = new Storage(fileDirectory, fileName, ui);
        taskList = new TaskList(storage, ui, storage.load());
    }
    
    public void run(InputStream stream) {
        // Set up Scanner for input and isBye boolean for while loop
        Scanner input = new Scanner(stream);
        boolean isBye = false;

        // Welcome message
        ui.showWelcome();

        // Continuously takes user input until "bye" command is given
        while (!isBye) {
            try {
                // performing action based on user command
                ui.showInputPrompt();
                String fullCommand = ui.readCommand(input);
                ui.showOpenLine();
                Command c = Parser.parse(fullCommand, taskList);
                c.execute(ui, taskList);
                isBye = c.isBye();
                
            } catch (DukeException e) {
                ui.showException(e);
                
            } catch (NumberFormatException e) { 
                ui.showException(new DukeException(DukeExceptionType.INVALID_TASK_INDEX));

            } catch (DateTimeParseException e) {
                ui.showException(new DukeException(DukeExceptionType.INVALID_DATETIME));
                
            } finally {
                ui.showCloseLine();
            }
        }

        input.close();
    }

    public static void main(String[] args) {
        new Duke("data", "duke.txt").run(System.in);
    }
}
