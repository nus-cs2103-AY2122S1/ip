import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Persistence {

    /**
     * In memory storage for log, for history, refer to the history variable
     */
    private static ArrayList< String> log = new ArrayList<>();

    /**
     * Adds the command to the log.
     * @param command Command to be added to the log
     */
    public static void addToLog(String command) {
        log.add(command);
    }

    /**
     * Prints log. No parameters needed
     */
    public static void print_log() {
        for (int i = 0;i < log.size(); i++ ) {
            StringBuilder sb = new StringBuilder();
            String currentCommand = log.get(i);
            sb.append((i + 1) + ". " + currentCommand);
            System.out.println(sb.toString());
        }
    }
}