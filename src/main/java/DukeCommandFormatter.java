import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class DukeCommandFormatter {
    private final Scanner scanner;
    private final PrintStream outputStream;

    public DukeCommandFormatter(InputStream inputStream, PrintStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.outputStream = outputStream;
    }

    public String nextCommand() {
        outputStream.print("Duke> ");
        return scanner.nextLine();
    }

    public void printOutputLine(String output) {
        outputStream.println(output);
    }
}
