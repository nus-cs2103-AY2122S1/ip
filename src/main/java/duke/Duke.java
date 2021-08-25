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

    /**
     * Runs the main logic of the Duke program.
     *
     * @param stream The desired stream for the program's input.
     */
    public void run(InputStream stream) {
        Scanner input = new Scanner(stream);
        boolean isBye = false;

        ui.showWelcome();

        while (!isBye) {
            try {
                ui.showInput();
                String fullCommand = ui.readCommand(input);
                ui.showOpenLine();
                Command c = Parser.parse(fullCommand, taskList);
                c.execute(ui, taskList);
                isBye = c.isBye();
                
            } catch (DukeException e) {
                ui.showException(e);
                
            } catch (NumberFormatException e) { 
                ui.showException(new DukeException(DukeExceptionType.INVALID_INDEX));

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
