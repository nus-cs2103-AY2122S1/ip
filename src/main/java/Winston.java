import java.util.Scanner;

public class Winston {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cmd = "Hi there! Winston reporting.\nWhat can I do for you?";

        while (!cmd.equals("bye")) {
            System.out.println(cmd);
            cmd = scan.next();
        }

        scan.close();
        System.out.println("See ya later!");
    }
}
