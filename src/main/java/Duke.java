import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Greet the user
        Printer.beautyPrint("Welcome to\n" +
                Printer.logo +
                "\tI'm Desmond,\n" +
                "\thow may I serve you?\n");

        // Initialize scanner to get user input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Echo to user input and exit when the input is "bye"
        while (true) {
            if (input.equals("bye")) {
                Printer.beautyPrint("Bye (*´▽｀)ノシ. Have a good day!\n");
                break;
            }
            Printer.beautyPrint(input + "\n");
            input = scanner.nextLine();
        }
    }
}

