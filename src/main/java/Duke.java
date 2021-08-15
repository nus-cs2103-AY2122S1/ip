import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<String> tasks = new ArrayList<>();

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
            } else if (userInput.equals("list")) {
                this.displayTasks();
            } else {
                tasks.add(userInput);
                printMessage(String.format("added: %s", userInput));
            }
        }
    }

    public void exit() {
        printMessage("Hope to see you again!! ^_^");
    }

    public void displayTasks() {
        int len = this.tasks.size();
        if (len == 0) {
            printMessage("You have no task!");
        } else {
            for (int i = 0; i < len; i++) {
                int num = i + 1;
                String text = this.tasks.get(i);
                printMessage(String.format("%d. %s", num, text));
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
