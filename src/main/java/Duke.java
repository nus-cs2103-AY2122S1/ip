import java.util.Scanner;

public class Duke {
    private static Message msg = new Message();

    public static void main(String[] args) {
        msg.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            msg.echo(input);
            input = sc.nextLine();
        }
        msg.exit();
    }
}
