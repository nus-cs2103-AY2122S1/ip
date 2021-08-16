import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Duke {
    /**
     * Simple string array to store inputs
     **/
    private final ArrayList<Task> list;

    /**
     * Basic constructor to initialise the list
     **/
    public Duke() {
        this.list = new ArrayList<>();
    }

    /**
     * Simple formatting tool to be used when printing commands
     *
     * @param text The text to be printed.
     */
    public static void printMsg(String text) {
        String bar = "===============================================";
        System.out.printf("%s\n%s\n%s\n", bar, text, bar);
    }

    /**
     * Method that listens and scans for user input.
     * Program will exit when the command "bye" is given to the scanner.
     */
    public void listen(){
        Scanner scanner = new Scanner(System.in);
        Duke.printMsg("Hello! I'm Duke\nWhat can I do for you?");
        boolean terminate = true;
        while (terminate && scanner.hasNextLine()) {
            String scannedLine = scanner.nextLine();
            Optional<DukeCommands> prefix = DukeCommands.getCommand(scannedLine.split(" ")[0]);
            DukeCommands command = prefix.orElseGet(() -> DukeCommands.INVALID);
            try {
                terminate = command.action.run(Parser.parseCommand(scannedLine), this.list);
            } catch(DukeException e) {
                printMsg(e.toString());
            }
        }
    }


}
