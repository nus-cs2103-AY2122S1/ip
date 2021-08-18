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
            String inputLower = input.toLowerCase();
            if (inputLower.replaceAll("\\s","").equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    Task item = list.get(i);
                    System.out.println(String.format("     %d. %s", (i + 1), item.toString()));
                }
            } else if (input.length() > 5 && inputLower.substring(0, 5).equals("done ")) {
                String modInput = input.replaceAll("\\s","");
                Task item = list.get(Integer.parseInt(modInput.substring(modInput.length() - 1)) - 1);
                item.markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println(String.format("       %s", item.toString()));
            } else if (input.length() > 5 && inputLower.substring(0, 5).equals("todo ")) {
                Todo t = new Todo(input.substring(5));
                list.add(t);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + t.toString());
                System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
            } else if (input.length() > 6 && inputLower.substring(0, 6).equals("event ")) {
                String[] splitString = input.substring(6).split("/at ", 2);
                Event e = new Event(splitString[0], splitString[1]);
                list.add(e);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + e.toString());
                System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
            } else if (input.length() > 9 && inputLower.substring(0, 9).equals("deadline ")) {
                String[] splitString = input.substring(6).split("/by ", 2);
                Deadline d = new Deadline(splitString[0], splitString[1]);
                list.add(d);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + d.toString());
                System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
            } else {

            }
            System.out.println("    ____________________________________________________________");
            input = in.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
