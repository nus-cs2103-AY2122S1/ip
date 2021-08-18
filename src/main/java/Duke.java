import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" +
                           "What can I do for you?");
        while (true) {
            String input = sc.nextLine();
            if (!input.equals("bye")) {
                System.out.println(input);
            } else {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
        sc.close();
    }
}
