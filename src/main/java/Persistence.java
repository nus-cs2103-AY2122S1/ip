import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Persistence {
    private static ArrayList< String> log = new ArrayList<>();

    public static void addToLog(String command) {
        log.add(command);
    }

    public static void print_log() {
        for (int i = 0;i < log.size(); i++ ) {
            StringBuilder sb = new StringBuilder();
            String currentCommand = log.get(i);
            sb.append((i + 1) + ". " + currentCommand);
            System.out.println(sb.toString());
        }
    }
}