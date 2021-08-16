import java.util.Scanner;

public class GreetingBot {

    public GreetingBot() {

    }

    public void startBot() {
        greet();
    }


    private void greet() {
        String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetingMessage);
        echo();
    }

    private void echo() {
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            String nextLine = inputScanner.nextLine();
            if (nextLine.equals("bye")) {
                exit();
            } else {
                System.out.println(nextLine);
            }
        }
    }

    private void exit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
}
