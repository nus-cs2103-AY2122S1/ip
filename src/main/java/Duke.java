import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Initial values
        String sepLine = "____________________________________________________________";
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);

        String start = "Hello! I'm Duke. \n"
                + "What can I do for you? \n"
                + sepLine;

        System.out.println(start);

        // Main loop for commands
        while (isRunning) {
            String next = sc.nextLine();
            if (next.equals("bye")) {
                System.out.println(sepLine + "\n" + "Bye. Hope to see you again soon!" + "\n" + sepLine);
                isRunning = false;
            } else {
                System.out.println(sepLine + "\n" + next + "\n" + sepLine);
            }
        }
    }
}
