import java.util.Scanner;

public class Duke {

    private static String[] tasks = new String[100];
    private static int counter = 0;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner scanner = new Scanner(System.in);
        boolean breakWhile = false;

        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "bye":
                    duke.exit();
                    breakWhile = true;
                    break;
                case "list":
                    Duke.displayList();
                    break;
                default:
                    duke.add(userInput);
                    break;
            }

            if (breakWhile) break;
        }
    }

    private void greet() {
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
        Duke.usersTurn();
    }

    private void exit() {
        System.out.print("\nDuke: Bye. Hope to see you again soon!");
    }

    private void add(String input) {
        System.out.println("\nDuke: I have added \"" + input + "\" into your task list.");
        Duke.usersTurn();
        tasks[counter] = input;
        counter++;
    }

    private static void displayList() {
        System.out.println("\nList\n--------------------");
        for (int i = 0; i < tasks.length; i++) {
            String task = tasks[i];
            if (task == null) {
                if (i == 0) System.out.println("List is empty!");
                break;
            }
            int index = i + 1;
            System.out.println(index + ". " + tasks[i]);
        }
        Duke.usersTurn();
    }

    private static void usersTurn() {
        System.out.print("\nYou: ");
    }
}
