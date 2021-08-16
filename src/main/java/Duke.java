import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner scanner = new Scanner(System.in);
        boolean breakWhile = false;

        while (scanner.hasNext()) {
            String firstWord = scanner.next();

            switch (firstWord) {
                case "bye":
                    duke.exit();
                    breakWhile = true;
                    break;
                case "done":
                    String stringIndex = scanner.next();
                    int index = Integer.parseInt(stringIndex) - 1;
                    Task.markDone(index);
                    System.out.println("\nDuke: Nice! I've marked this task as done:\n" + Task.retrieveTask(index));
                    break;
                case "list":
                    Task.displayList();
                    break;
                default:
                    String remaining = firstWord.concat(" " + scanner.nextLine());
                    System.out.println("\nDuke: I have added \"" + remaining + "\" into your task list.");
                    Task.add(remaining);
                    break;
            }
            if (breakWhile) break;
            Duke.usersTurn();
        }
    }

    private void greet() {
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
        Duke.usersTurn();
    }

    private void exit() {
        System.out.print("\nDuke: Bye. Hope to see you again soon!");
    }

    private static void usersTurn() {
        System.out.print("\nYou: ");
    }
}
