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
    
    public void run() {
        Scanner input = new Scanner(System.in);
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
                ui.showException(new DukeException(DukeExceptionType.INVALIDINDEX));

            } catch (DateTimeParseException e) {
                ui.showException(new DukeException(DukeExceptionType.INVALIDDATETIME));
                
            } finally {
                ui.showCloseLine();
            }
        }

        input.close();
    }

    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }
}
