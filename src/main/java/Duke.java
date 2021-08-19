import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String greetings = "Hello! I'm Grace" + "\n" + "How can I help you?";
        System.out.println(greetings);
        int quit = 0;
        while (quit == 0) {
            String next_line = scan.next();
            if (next_line.equals("bye")) {
                System.out.println("Bye bye! Hope to see you again soon!");
                quit = 1;
            }
            else {
                System.out.println(next_line);
            }
        }
    }
}
