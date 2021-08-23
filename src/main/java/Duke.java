import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class Duke {
    private static DukeHandler dukeHandler;
    private static FileHandler fileHandler;
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        loadFile();


        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("d/M/yyyy")
                .optionalStart()
                .appendPattern(" HHmm")
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
        while (true) {
            String input = sc.nextLine();
            if (dukeHandler.isExit(input)) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            } else {
                try {
                    String[] results = dukeHandler.parseInput(input);
                    for (int i = 0; i < results.length; i++) {
                        System.out.println(results[i]);
                    }
                } catch (Exception e) {
                    System.out.println("\t" + e);
                }
            }
        }
    }

    private static void loadFile() {
        try {
            fileHandler = new FileHandler("duke.txt");
            fileHandler.loadTasks();
            dukeHandler = new DukeHandler(fileHandler.getTasks(), fileHandler);
        } catch (IOException | DukeException e) {
            System.out.println(e);
        }
    }
}
