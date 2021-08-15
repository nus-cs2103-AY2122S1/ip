import java.util.ArrayList;
import java.util.List;

/**
 *  This class handles user commands when keyed onto the terminal
 * @author Ryan Tian Jun
 */
public class FeatureMain {
    private String currentCommand;
    private static List<String> commands = new ArrayList<String>();

    public FeatureMain(String command) {
        if (command.toLowerCase().equals("list")) {
            this.currentCommand = command.toLowerCase();
        } else {
            this.currentCommand = command.toLowerCase();
            FeatureMain.commands.add(command);
        }
    }

    /**
     * Processes user commands to return a specific action
     *
     * @return Specific feature pertaining to command
     */
    public String processCommand() {
        if (this.currentCommand.equals("list")) {
            return userCommands();
        } else {
            return addCommand();
        }
    }

    /**
     * Echos user commands
     *
     * @return returns an echo of the users commands
     */
    private String echoCommand() {
        System.out.println(currentCommand);
        newLine();
        return currentCommand;
    }

    /**
     * Adds user commands to history ArrayList commands
     *
     * @return Notifies the user that their command has been added
     */
    private String addCommand() {
        System.out.println("added: " + currentCommand);
        newLine();
        return "added: " + currentCommand;
    }

    /**
     * Prints past commands
     *
     * @return returns a printout of all the past user commands
     */
    private static String userCommands() {
        int count = 1;
        for (String item : commands) {
            System.out.println(count + ". " + item);
            count++;
        }
        newLine();
        return commands.toString();
    }

    // Simply creates a new line in the terminal
    private static String newLine() {
        System.out.println("\n");
        return "\n";
    }
}
