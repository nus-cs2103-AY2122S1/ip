import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetingMessage);
        boolean userExit = false;
        while (!userExit) {
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "bye":
                    userExit = true;
                    break;
                default:
                    System.out.println(userInput);
            }
        }
        if (userExit) {
            String goodbyeMessage = "Bye. Hope to see you again soon!";
            System.out.println(goodbyeMessage);
        }
    }
}
