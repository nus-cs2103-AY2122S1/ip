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
        Task[] tasks = new Task[100];
        int numTasks = 0;

        while (true) {
            String input = scanner.nextLine();
            String[] inputWords = input.split(" ");
            String command = inputWords[0];
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (command.equals("list")) {
                printLineDecoration();
                for (int i = 0; i < numTasks; i++) {
                    System.out.println(i + 1 + "." + tasks[i].showStatus());
                }
                printLineDecoration();
                System.out.println("\n");
            } else if (command.equals("done")) {
                System.out.println("inside done\n");
                try {
                    int taskIndex = Integer.parseInt(inputWords[1]) - 1;
                    tasks[taskIndex].toggleDone();
                    printLineDecoration();
                    System.out.println("Nice! I've marked this task as done:\n"
                            + tasks[taskIndex].showStatus());
                    printLineDecoration();
                    System.out.println();

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid index!\n");
                }
            } else {
                printLineDecoration();
                tasks[numTasks] = new Task(input);
                numTasks++;
                System.out.println("added: " + input);
                printLineDecoration();
                System.out.println("\n");
            }
        }


    }
}
