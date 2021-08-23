import java.util.Scanner;

public class Ui {

    Scanner scanner = new Scanner(System.in);

    protected String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a line.
     */
    protected void printLine() {
        System.out.println("__________________________________________________________");
    }

    /**
     * Prints the greeting message.
     */
    protected void greetUser() {
        printLine();
        System.out.println("Greetings! I am Duke.");
        System.out.println("How can I assist you?");
        printLine();
    }

    /**
     * Prints the farewell message.
     */
    protected void farewellUser() {
        printLine();
        System.out.println("Bye! See you soon!");
        printLine();
    }

    protected void printsMessage(String message) {
        System.out.println(message);
    }

    protected void printTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, taskList.tasks.get(i));
        }
    }
}
