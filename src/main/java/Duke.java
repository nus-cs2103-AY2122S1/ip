import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        echo();
    }

    public static void echo() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);

        String s = "";

        while (!s.equals("Bye")) {
            s = in.nextLine();
            System.out.println("---------");
            System.out.println(s);
            System.out.println("---------");
        }

        System.out.println("Bye. Hope to see you again soon!");
        in.close();
        return;
    }
}
