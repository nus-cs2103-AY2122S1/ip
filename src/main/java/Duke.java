import java.util.Scanner;

public class Duke {
    private static int listSize = 0;
    private static String[] list = new String[100]; // List to store all the tasks

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");



        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                // Exit loop and thus program when input is bye
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            handleInput(input);
        }
    }

    private static void handleInput(String input) {
        if (input.equals("list")) {
            for (int i = 0; i < listSize; i++) {
                System.out.println((i + 1) + ". " + list[i]);
            }
        } else {
            list[listSize] = input;
            listSize++;
            System.out.println("added: " + input);
        }
    }
}
