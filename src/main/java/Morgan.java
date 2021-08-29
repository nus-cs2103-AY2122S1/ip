import tasks.TaskList;
import commands.Command;
import exceptions.MorganException;

public class Morgan {
    private static final String EXIT_KEYWORD = "bye";

    public static void main(String[] args) {
        Ui ui = new Ui();
        try {
            ui.printStartDisplay();
            Storage s = new Storage();
            TaskList tl = new TaskList();
            s.load(tl);
            CommandParser p = new CommandParser();
            String userInput = ui.getInput();
            while (!userInput.trim().toLowerCase().equals(EXIT_KEYWORD)) {
                try {
                    Command command = p.getCommand(userInput);
                    String output = command.execute(tl);
                    s.save(tl);
                    ui.print(output);
                } catch (MorganException e) {
                    ui.print(e.getMessage());
                }
                userInput = ui.getInput();
            }
            ui.printEndDisplay();
        } catch (MorganException e) {
            ui.print(e.getMessage());
        }
    }
}
