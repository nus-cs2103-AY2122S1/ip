import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        String bye = "bye";
        String list = "list";

        List<String> userList = new ArrayList<>();

        while (true) {
            String inp = sc.nextLine();
            if (inp.equals(bye)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inp.equals(list)) {
                int counter = 1;
                for (String str:userList) {
                    System.out.println(counter + ". " + str);
                    counter++;
                }
            } else {
                userList.add(inp);
                System.out.println("added: " + inp);
            }
        }
    }
}
