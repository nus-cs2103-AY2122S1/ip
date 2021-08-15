import java.util.Scanner;

public class Duke {
    public void run() {
        this.greetUser();
        this.echo();
        this.exit();
    }

    public void printMessage(String message) {
        String formatDisplay = String.format("    %s", message);
        System.out.println(formatDisplay);
    }

    public void greetUser() {
        String greetMessage = "Hello! I'm Saitama";
        String detailsMessage = "I do 100 sit-ups, 100 push-ups, 100 squats and a 10 kilometer run every day! No cap";
        printMessage(greetMessage);
        printMessage(detailsMessage);
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);
        boolean continueEcho = true;
        while (continueEcho) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                continueEcho = false;
            } else {
                printMessage(userInput);
            }
        }
    }

    public void exit() {
        printMessage("Hope to see you again!! ^_^");
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
