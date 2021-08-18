import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String introMessage = "Hello! I'm Lawbringer!\n" +
                "What can i do for you?";
        System.out.println(introMessage);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(userInput);
            }
        }
    }
}
