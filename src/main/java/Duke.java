import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskArr = new ArrayList<String>();
        String command = sc.nextLine();
        while (! command.equals("bye")) {
            if (command.equals("list")) {
                for(int i = 0; i < taskArr.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + ". " + taskArr.get(i));
                }
            } else {
                taskArr.add(command);
                System.out.println("added: " + command);
            }
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
