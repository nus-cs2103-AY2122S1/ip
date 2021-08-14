import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    final DukeCommandFormatter commandFormatter = new DukeCommandFormatter(System.in, System.out);
    final List<String> list = new ArrayList<>();
    final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    public void start() {
        printWelcomeMessage();
        boolean shouldListen;
        do {
            String command = commandFormatter.nextCommand();
            shouldListen = processCommand(command);
        } while (shouldListen);
        printExitMessage();
    }

    public void printWelcomeMessage() {
        commandFormatter.printOutputLine("Hello from\n" + logo);
    }

    public void printExitMessage() {
        commandFormatter.printOutputLine("Goodbye from\n" + logo);
    }

    /**
     * Processes the given command (a line). Returns true if more commands are to be listened to.
     * @param command The command to be processed.
     * @return If Duke should continue listening to commands.
     */
    public boolean processCommand(String command) {
        switch (command) {
            case "list":
                for (int i = 0; i < list.size(); i++) {
                    String item = list.get(i);
                    commandFormatter.printOutputLine(String.format("%d. %s", i + 1, item));
                }
                return true;
            case "bye":
                return false;
            default:
                list.add(command);
                commandFormatter.printOutputLine(String.format("Added: %s", command));
                return true;
        }
    }

    private static class DukeCommandFormatter {
        private Scanner scanner;
        private PrintStream outputStream;

        DukeCommandFormatter(InputStream inputStream, PrintStream outputStream) {
            this.scanner = new Scanner(inputStream);
            this.outputStream = outputStream;
        }

        String nextCommand() {
            outputStream.print("Duke> ");
            return scanner.nextLine();
        }

        void printOutputLine(String output) {
            outputStream.println(output);
        }
    }
}
