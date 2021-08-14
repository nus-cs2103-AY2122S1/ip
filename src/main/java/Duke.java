import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        DukeCommandFormatter commandFormatter = new DukeCommandFormatter(System.in, System.out);
        String command;
        while (!(command = commandFormatter.nextCommand()).equals("bye")){
            processCommand(commandFormatter, command);
        }
    }

    public static void processCommand(DukeCommandFormatter commandFormatter, String command) {
        commandFormatter.printOutputLine(command);
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
