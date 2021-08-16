import java.util.Scanner;

public class Jared {
    public static void main(String[] args) {
        String intro = "____________________________________________________________\n" +
                "Hello! I'm Jared\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        Scanner scan = new Scanner(System.in);

        System.out.println(intro);
        while (true) {
            String next = scan.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(next);
            }
        }

    }
}
