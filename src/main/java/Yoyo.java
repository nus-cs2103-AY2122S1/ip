import java.util.Scanner;

public class Yoyo {

    /**
     * Prints a decoration line for output.
     */
    private static void printLineDecoration() {
        System.out.println("========================================================================");
    }

    public static void main(String[] args) {
        String greetings = "Hello! I'm Yoyo\n"
                + "What can I do for you?\n";
        System.out.println(greetings);
        Scanner scanner = new Scanner(System.in);
        String[] texts = new String[100];
        int numTexts = 0;

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (input.equals("list")) {
                printLineDecoration();
                for (int i = 0; i < numTexts; i++) {
                    System.out.println(i + 1 + ". " + texts[i]);
                }
                printLineDecoration();
                System.out.println("\n");
            } else {
                printLineDecoration();
                texts[numTexts] = input;
                numTexts++;
                System.out.println("added: " + input);
                printLineDecoration();
                System.out.println("\n");
            }
        }


    }
}
