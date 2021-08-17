import java.sql.SQLOutput;
import java.util.Scanner;

public class Skeltal {
    static Scanner sc = new Scanner(System.in);
    private static String line = "*********************************************";

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("Hello! I am Skeltal! \n"
            + "What can I do for you?");
        System.out.println(line);

        boolean shutdown = false;
        while (!shutdown) {
            String userReply = sc.nextLine();
            System.out.println(line);
            if (userReply.equals("bye")) {
                System.out.println("Thanks for chatting! Hope to see you again soon! ");
                shutdown = true;
                System.out.println(line);
                continue;
            }
            echo(userReply);
            System.out.println(line);
        }

    }

    public static void echo(String userReply) {
        System.out.println(userReply);
    }

}
