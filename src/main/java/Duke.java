import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner scanner = new Scanner(System.in);
        Task task = new Task();
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
                    task.markDone(index);
                    System.out.println("\nDuke: Nice! I've marked this task as done:\n" + Task.retrieveTask(index));
                    break;
                case "list":
                    Task.displayList();
                    break;
                default:
                    String remaining = firstWord.concat(" " + scanner.nextLine());
                    int lengthBefore = Task.listLength();
                    try {
                        task.add(remaining);
                        System.out.println("\nDuke: Got it. I have added this task:\n" +
                                Task.retrieveTask(lengthBefore) + "\nNow you have " +
                                Task.listLength() + " tasks in the list.");
                        break;
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
            }
            if (breakWhile) break;
        }
    }

    private void greet() {
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
    }

    private void exit() {
        System.out.println("\nDuke: Bye. Hope to see you again soon!");
    }
}
