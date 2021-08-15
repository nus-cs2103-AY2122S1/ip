import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner scanner;
    private final PrintStream outputStream;

    public Ui(InputStream inputStream, PrintStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.outputStream = outputStream;
    }

    public String nextCommand() {
        outputStream.print("Duke> ");
        return scanner.nextLine();
    }

    public void outputLine(String output) {
        outputStream.println(output);
    }

    public void printWelcomeMessage() {
        outputLine("Hello from\n" + logo);
    }

    public void printExitMessage() {
        outputLine("Goodbye from\n" + logo);
    }

}
