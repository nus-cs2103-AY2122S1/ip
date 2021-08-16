import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I am Duke. What can I do for you?");
        waitResponse();
    }

    private static void waitResponse() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()){
            String next = scanner.next();
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                return;
            } else {
                System.out.println(next);
                waitResponse();
            }
        }
    }
}
