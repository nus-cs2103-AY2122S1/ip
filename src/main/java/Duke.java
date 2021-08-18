import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        String input = in.nextLine();
        while(!input.replaceAll("\\s","").toLowerCase().equals("bye")) {
            if (input.replaceAll("\\s","").toLowerCase().equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("     %d. %s", (i + 1), list.get(i)));
                }
                System.out.println("    ____________________________________________________________");
                input = in.nextLine();
            } else {
                list.add(input);
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + input);
                System.out.println("    ____________________________________________________________");
                input = in.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
