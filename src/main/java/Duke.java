import java.time.Period;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {

    private final String LOGO =
                    "░░░░░░░░░░░░░░░░▄▄▄███████▄▄░░░░░░░░░░░░\n" +
                    "░░░░░░░░░░░░░░▄███████████████▄░░░░░░░░░\n" +
                    "░░░░░░░░░░░░░█▀▀▀▄░░░░█████▀▀███▄░░░░░░░\n" +
                    "░░░░░░░░░░░░█░░▄░░█▄▄█░░░░▀▄▄█████░░░░░░\n" +
                    "░░░░░░░░░▄▀▀▀▄▄▀▀▀▀▀▀▄░░▀░░█▀▀▀░▀██░░░░░\n" +
                    "░░░░░░░▄▀░░░░░█░░░░░░▀▄▄▄▄█░░░░░░░▀▄░░░░\n" +
                    "░░░░░░▄▀░░░░░▄█▀▄▄▄▄░░░░░▄░░░░░░░░░█░░░░\n" +
                    "░░░░░░█░░░░░▄█▀▄▄▄▄▄▄▄▄▄▀▀░░░░░░░░░▀▄░░░\n" +
                    "░░░░░█░░░░░░▀█▄░░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▀▄░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▄█░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░▄▀░░░░░░░░▀▄░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░▀▄░░░▄░░░░░█░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▀▀▀▀▀█▄▄▀░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▄██░░\n" +
                    "░░░░░█▄░░░░░░░░░░░░░░░░░░░░░░░░░░▄▀▀▄▀▄▄\n" +
                    "░░░░▄█▀▄░░░░░░░░░░░░░░░░░░░░░░▄▄▀░░▄▀░░░\n" +
                    "░▄░▀░░█░▀▄▄░░░░░░░░░░░░░░░▄▄▄▀░░░▄▀░░░░░\n" +
                    "▀░░░░░░▀▄░░▀▄░░░░░░░░▄▄▄▀▀░░░░▄▀▀░░░░░░░";
    private final String HORIZONTAL_LINE =
                    "\t  .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .--.\n" +
                    "\t:::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\\n" +
                    "\t'      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `";
    private final String GREETING = "Hallo! My name's Peter!\n\tHow may I be of service to you?";
    private final String GOODBYE = "Good day! I'm gonna find Lois if you're not using me!";
    private final String TASK_REPORT = "Here are all your tasks! No Procrastination!";
    private Task[] taskTracker = new Task[100];
    private int taskNo = 0;

    private void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    private void printNewLine(String input) {
        System.out.println("\t" + input);
    }

    private void response(String input) {
        printHorizontalLine();
        printNewLine(input);
        printHorizontalLine();
    }

    private void addTask(String input){
        taskTracker[taskNo++] = new Task(input);
        response("Task Added: " + input);
    }

    private String formatTask() {
        StringBuilder formattedTask = new StringBuilder();
        formattedTask.append(TASK_REPORT + "\n\t");
        for (int i = 0; i < taskTracker.length; i++) {
            if (taskTracker[i] != null) {
                String eachLine = (i + 1) + ". " + taskTracker[i].toString() + "\n\t";
                formattedTask.append(eachLine);
            }
        }
        formattedTask.append("(end)");
        return formattedTask.toString();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine().trim();
            if (input.equals("bye")) {
                response(GOODBYE);
                return;
            }else if (input.equals("list")){
                response(formatTask());
            }else {
                addTask(input);
            }

        }
    }

    void initiate() {
        System.out.println("\t" + LOGO.replaceAll("\n", "\n\t"));
        response(GREETING);
        this.run();
    }

    public static void main(String[] args) {
        new Duke().initiate();
    }
}