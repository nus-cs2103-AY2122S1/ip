import tasks.TaskList;
import commands.Command;
import exceptions.DukeException;

public class Duke {
    private static final String EXIT_KEYWORD = "bye";

    public static void main(String[] args) {
        Ui ui = new Ui();
        try {
            ui.printStartDisplay();
            Storage s = new Storage();
            TaskList tl = new TaskList();
            s.load(tl);
            InputParser p = new InputParser();
            String userInput = ui.getInput();
            while (!userInput.trim().toLowerCase().equals(EXIT_KEYWORD)) {
                try {
                    Command command = p.getCommand(userInput);
                    String output = command.execute(tl);
                    s.save(tl);
                    ui.print(output);
                } catch (DukeException e) {
                    ui.print(e.getMessage());
                }
                userInput = ui.getInput();
            }
            ui.printEndDisplay();
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
    }
}
