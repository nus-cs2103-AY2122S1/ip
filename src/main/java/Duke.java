import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        String input = in.nextLine();
        while(!input.replaceAll("\\s","").toLowerCase().equals("bye")) {
            if (input.replaceAll("\\s","").toLowerCase().equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    Task item = list.get(i);
                    System.out.println(String.format("     %d.[%s] %s", (i + 1), item.getStatusIcon(), item.description));
                }
            } else if (input.length() > 5 && input.toLowerCase().substring(0, 5).equals("done ")) {
                String modInput = input.replaceAll("\\s","");
                Task item = list.get(Integer.parseInt(modInput.substring(modInput.length() - 1)) - 1);
                item.markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println(String.format("       [X] %s", item.description));
            } else {
                list.add(new Task(input));
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + input);
            }
            System.out.println("    ____________________________________________________________");
            input = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
