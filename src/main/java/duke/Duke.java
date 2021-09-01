package duke;

import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.exceptions.DukeException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt", "data/");
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = ui.getIsExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("OOPS! Please input date in this format: yyyy-mm-dd");
            }
        }
    }
    
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}



