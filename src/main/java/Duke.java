import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        startDeanBot();
    }

    // Starts Dean bot.
    private static void startDeanBot() {
        String logo = "   ____                       \n"
                +     "  |  _ \\  ___  __ _ _ ___    \n"
                +     "  | | | |/ _ \\/ _` | '_  \\  \n"
                +     "  | |_| |  __| (_| | | | |    \n"
                +     "  |____/ \\___|\\__,_|_| |_|  \n";
        printDoubleBorder();
        System.out.println("Hello! I'm:\n" + logo + "\nHow may I help you?");
        printDoubleBorder();

        Scanner input = new Scanner(System.in);  // Create a Scanner object

        while (input.hasNextLine()) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                printSingleBorder();
                System.out.println("Bye. Hope to see you again soon!");
                printSingleBorder();
                break;
            } else {
                printSingleBorder();
                System.out.println(userInput);
                printSingleBorder();
            }
        }
    }

    // Print double line border.
    private static void printDoubleBorder() {
        System.out.println("==================================================");
    }

    // Prints single line border.
    private static void printSingleBorder() {
        System.out.println("--------------------------------------------------");
    }
}
