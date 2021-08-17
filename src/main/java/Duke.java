import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static ArrayList<String> list = new ArrayList<String> ();

    public static void main(String[] args) {
        echo();
    }

    public static void echo() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);

        String s = in.nextLine();

        while (!s.equals("bye")) {
            System.out.println("---------");
            System.out.println(s);
            System.out.println("---------");
            s = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        in.close();
        return;
    }
}
