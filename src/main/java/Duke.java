import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Greet the user
        Printer.prettyPrint("Welcome to\n" +
                Printer.logo +
                "\tI'm Desmond,\n" +
                "\thow may I serve you?\n");

        // Initialize string array to store the list
        String[] tasks = new String[100];
        int numOfTask = 0;

        // Initialize scanner to get user input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Add task based on user input
        // and exit when the input is "bye"
        while (true) {
            if (input.equals("bye")) {
                Printer.prettyPrint("Bye (*´▽｀)ノシ. Have a good day!\n");
                break;
            }
            switch (input) {
                case "list":
                    Printer.prettyPrint(Printer.listTask(tasks));
                    break;
                default:
                    tasks[numOfTask++] = input;
                    Printer.prettyPrint("added: " +
                            input + "\n");
            }
            input = scanner.nextLine();
        }
    }
}

