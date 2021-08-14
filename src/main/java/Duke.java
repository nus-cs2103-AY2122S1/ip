import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");

        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while(!in.equals("bye")) {
            System.out.println(in);
            in = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
