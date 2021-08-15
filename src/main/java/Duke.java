import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(
            "Lollipop: Hello! I am your personal chatbot, Lollipop! " +
            "What would you like to do today?");
        System.out.print("You: ");
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("Lollipop: " + command);
            System.out.print("You: ");
            command = scanner.nextLine();
        }

        System.out.println(
            "Lollipop: See you again soon!");
    }
}
