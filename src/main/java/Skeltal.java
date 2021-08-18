import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class Skeltal {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SkeltalException {

        Display.introduction();

        boolean shutdown = false;
        while (!shutdown) {
            String userReply = sc.nextLine();
            if (userReply.equals("bye")) {
                System.out.println(Display.line);
                System.out.println("Thanks for chatting! Hope to see you again soon! ");
                shutdown = true;
                System.out.println(Display.line);
                continue;
            } else {
                Display.response(userReply);
            }
        }
    }
}
