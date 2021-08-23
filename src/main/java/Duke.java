import commands.Command;
import commands.DukeException;
import tasks.TaskList;

import java.util.Scanner;

public class Duke {
    private static final String EXIT_KEYWORD = "bye";

    public static void main(String[] args) {
        // TODO: Add enum CommandKeyword "done" "deadline" "event"
        Ui.printStartDisplay();
        TaskList tl = new TaskList();
        Storage s = new Storage();
        Parser p = new Parser();
        String userInput = Ui.getInput();
        while (!userInput.trim().toLowerCase().equals(EXIT_KEYWORD)) {
            try {
                Command command = p.getCommand(userInput);
                String output = command.execute(tl);
                s.save(tl);
                Ui.print(output);
            } catch (DukeException e) {
                Ui.print(e.getMessage());
            }
            userInput = Ui.getInput();
        }
        Ui.printEndDisplay();
    }
}
