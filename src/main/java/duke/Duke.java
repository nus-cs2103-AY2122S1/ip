package duke;

import duke.command.DukeCommand;
import duke.command.ExitCommand;
import duke.exception.CorruptedFileException;
import duke.exception.DukeException;

/**
 * Main class of duke package.
 */
public class Duke {
    /**
     * Static method to start the bot.
     */
    private static void runDuke() {
        DukeList list = new DukeList();
        Ui.greet();
        Ui.display(Storage.load(list));
        Ui.begin();
        while (true) {
            String input = Ui.readLine();
            DukeCommand command = Parser.parseInput(input);
            if (command instanceof ExitCommand) {
                break;
            }
            try {
                Ui.display(command.run(list));
            } catch (DukeException e) {
                Ui.display(e.getMessage());
            }
        }
        try {
            Storage.saveFile(list);
            Ui.display("File saved");
        } catch(CorruptedFileException e) {
            Ui.display("Error saving file");
        }
        Ui.bye();
    }

    public static void main(String[] args) {
        runDuke();
    }

}
