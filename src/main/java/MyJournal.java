import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String ans = "";
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");
        while (true) {
            ans = reader.nextLine();
            if (!ans.equals("bye")) {
                System.out.println(ans);
            } else {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
