import java.util.Scanner;
public class Duke {

    private static String[] userInput = new String[100];
    private static boolean isBye = false;

    public static void setArray()
    {
        for(int i = 0; i < 100; i++)
        {
            userInput[i] = null;
        }
    }

    public static void getInput(Scanner scanner, int counter) {
        while (!isBye) {
            String input = scanner.nextLine();
            if (input.equals("bye") || input == "bye") {
                System.out.println("  ---------------------------------------------");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("  ---------------------------------------------");
                isBye = true;
                scanner.close();
            } else if (input.equals("list") || input == "list") {
                System.out.println("  ---------------------------------------------");
                int point = 0;
                while (userInput[point] != null) {
                    System.out.println("    " + (point + 1) + ". " + userInput[point]);
                    point++;
                }
                System.out.println("  ---------------------------------------------");
            } else {
                System.out.println("  ---------------------------------------------");
                System.out.println("    added: " + input);
                System.out.println("  ---------------------------------------------");
                userInput[counter] = input;
                counter++;
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("  ---------------------------------------------");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("  ---------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        setArray();
        getInput(scanner, counter);

    }
}
