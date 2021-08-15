import java.util.Scanner;

public class Duke {
    private static final String LINE = "     ________________________________________\n"; // 5 spaces, 40 dashes
    private static final String INDENT = "     "; // 5 spaces
    private boolean isRunning;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.greet();
        duke.echo();
    }

    public Duke() {
        isRunning = true;
    }

    public void greet() {
        String greeting = LINE
            + INDENT + "Hello! I'm Duke\n"
            + INDENT + "What can I do for you?\n"
            + LINE;
        System.out.println(greeting);
    }

    public void echo() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning){
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                // Exit
                System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
                isRunning = false;
            } else {
                System.out.println(LINE + INDENT + userInput + "\n" + LINE);
            }
        }
    }

}
