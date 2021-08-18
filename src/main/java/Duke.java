import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> list;

    public static void main(String[] args) {
        list = new ArrayList<>();
        startDeanBot();
    }

    // Starts Dean bot.
    private static void startDeanBot() {
        String logo = "   ____                       \n"
                +     "  |  _ \\  ___  __ _ _ ___    \n"
                +     "  | | | |/ _ \\/ _` | '_  \\  \n"
                +     "  | |_| |  __| (_| | | | |    \n"
                +     "  |____/ \\___|\\__,_|_| |_|  \n";
        printDoubleLineBorder();
        System.out.println("Hello! I'm\n" + logo + "\nHow may I help you?");
        printDoubleLineBorder();

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch(input) {
                case "bye":
                    printSingleLineBorder();
                    System.out.println("Bye. Hope to see you again soon!");
                    printSingleLineBorder();
                    break;
                case "list":
                    printSingleLineBorder();
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ". " + list.get(i));
                    }
                    printSingleLineBorder();
                    break;
                default:
                    list.add(input);
                    printSingleLineBorder();
                    System.out.println("Added: " + input);
                    printSingleLineBorder();
            }
        }
    }

    // Print double line border.
    private static void printDoubleLineBorder() {
        System.out.println("==================================================");
    }

    // Prints single line border.
    private static void printSingleLineBorder() {
        System.out.println("--------------------------------------------------");
    }
}
