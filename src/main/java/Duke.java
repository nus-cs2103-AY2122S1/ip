import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<String> tasks = new ArrayList<>();
    static Message msg = new Message();

    public static void main(String[] args) {
        msg.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                msg.list();
            } else {
                msg.addTask(input);
                tasks.add(input);
            }
            input = sc.nextLine();
        }
        msg.exit();
    }
}
