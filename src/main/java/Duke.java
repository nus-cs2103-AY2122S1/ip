import java.util.Scanner;

public class Duke {

    public static String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        do {
            input = getUserInput(scanner);
            System.out.println(input);
        } while (!input.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
    }
}
