import tasks.TaskList;
import commands.Command;
import exceptions.MorganException;

public class Morgan {
    private static final String EXIT_KEYWORD = "bye";

    public static void main(String[] args) {
        Morgan.launch();
    }

    private static void launch() {
        // Initialize UI
        Ui ui = new Ui();
        ui.printStartDisplay();

        // Initialize TaskList and CommandParser
        TaskList tl = new TaskList();
        CommandParser p = new CommandParser();

        try {
            // Initialize and load storage
            Storage s = new Storage();
            s.load(tl);

            // Read user input and check whether input is exit keyword
            String userInput = ui.getInput();
            boolean isExitKeyword = userInput.trim().equalsIgnoreCase(EXIT_KEYWORD);
            while (!isExitKeyword) {
                try {
                    // Obtain and execute command to retrieve output message
                    Command command = p.getCommand(userInput);
                    String output = command.execute(tl);

                    // Save updated task list
                    s.save(tl);

                    // Print output message
                    ui.print(output);
                } catch (MorganException e) {
                    ui.print(e.getMessage());
                }

                // Retrieves user input and check if input is exit keyword
                userInput = ui.getInput();
                isExitKeyword = userInput.trim().equalsIgnoreCase(EXIT_KEYWORD);
            }

            // Print end display
            ui.printEndDisplay();
        } catch (MorganException e) {
            ui.print(e.getMessage());
        }
    }
}
