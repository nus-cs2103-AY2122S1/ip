import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        greeting();
        echo();
        exit();
    }

    private static void greeting() {
        System.out.println("Aloha! I'm Sophia\nWhat can I do for you?\n");
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command);
            command = sc.nextLine();
        }
        sc.close();
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
