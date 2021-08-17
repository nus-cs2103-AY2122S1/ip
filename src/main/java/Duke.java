import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String chatbot = "Ailurus";
        String you = "You";
        boolean endChat = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println(String.format("%s: Hello! I'm %s. What can I do for you?", chatbot, chatbot));
        while (!endChat) {
            System.out.print(you + ": ");
            String input = scanner.next();
            if (input.equals("bye")) {
                endChat = true;
            } else {
                System.out.println(String.format("%s: %s", chatbot, input));
            }
        }
        System.out.println(String.format("%s: Bye. Hope to see you again soon!", chatbot));
    }
}
