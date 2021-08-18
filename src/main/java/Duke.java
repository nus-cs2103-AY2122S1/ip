import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String welcomeLine = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String goodbyeLine = "Bye. Hope to see you again soon!";
        System.out.println(welcomeLine);
        boolean isRunning = true;
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.next();
            if (input.equals("bye")) {
                isRunning = false;
                System.out.println(goodbyeLine);
            } else {
                System.out.println(input);
            }
        } while (isRunning);
    }
}
