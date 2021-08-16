import java.util.Scanner;

public class Yoyo {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Yoyo\n"
                + "What can I do for you?\n";
        System.out.println(greetings);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else {
                System.out.println("========================================================================");
                System.out.println(input);
                System.out.println("========================================================================");
            }
        }


    }
}
