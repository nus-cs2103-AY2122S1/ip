import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int i = 1;
                for (Task item : list) {
                    System.out.println(i + ". [" + item.getStatusIcon() + "] " + item.getDescription());
                    i++;
                }

            } else if (input.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Task temp = list.get(index);
                temp.setStatus(true);
                System.out.println("Marked as done:\n" + temp.getDescription());

            }
            else {
                list.add(new Task(input));
                System.out.println("Added: " + input);
            }
        }
    }
}
