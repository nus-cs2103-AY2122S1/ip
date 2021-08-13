import java.util.Scanner;

public class Duke implements Runnable {
    private final String BORDER = "--------------------------------------------";
    private final String GREETING = "Hello! I'm Duke, what can I do for you?";
    private final String FAREWELL = "Bye. Hope to see you again soon!";

    private void printDuke(String str) {
        System.out.printf(
            String.format("%s\n%s\n%s\n", BORDER, str, BORDER)
                .replaceAll("(?m)^", "\t")
        );
    }

    private void execute(String input) {
        printDuke(input);
    }

    public void run() {
        printDuke(GREETING);

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!(input = sc.nextLine().trim()).equals("bye")) {
            execute(input);
        }

        printDuke(FAREWELL);
        sc.close();
    }

    public static void main(String[] args) {
       new Duke().run();
    }
}
