import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = "Hello I'm Duke!\nWhat can I do for you?";
        boolean run = true;
        while (run) {
            printMessage(message);
            message = sc.nextLine();
            run = ! message.equals("bye");
        }
        printMessage("Goodbye for now!");
    }

    private static void printMessage(String message) {
        System.out.println("-------------------------");
        System.out.println(message);
        System.out.println("-------------------------");
    }
}
