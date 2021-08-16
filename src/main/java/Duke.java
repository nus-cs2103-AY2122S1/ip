import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                duke.exit();
                break;
            }
            duke.echo(userInput);
        }
    }

    private void greet() {
        System.out.print("Duke: Hello! I'm Duke\nWhat can I do for you?\n\nYou: ");
    }

    private void exit() {
        System.out.print("\nDuke: Bye. Hope to see you again soon!");
    }

    private void echo(String input) {
        System.out.print("\nDuke: " + input + "\n\nYou: ");
    }
}
