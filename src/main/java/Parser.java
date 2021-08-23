import java.time.DateTimeException;

public class Parser {
    public static String[] parse(String command) {
        return command.split(" ", 2);
    }
}
