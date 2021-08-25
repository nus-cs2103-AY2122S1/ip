import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Magnolia\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while(true) {
            String input = sc.nextLine();
            String first_word = input.split(" ")[0];


            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int i = 1;
                for (Task item : list) {
                    System.out.println(i + ". " + item.getTaskType() + item.getStatusIcon() + " " + item.getDescription());
                    i++;
                }

            } else if (first_word.equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Task temp = list.get(index);
                temp.setStatus(true);
                System.out.println("Marked as done:\n" + temp.getDescription());

            }
            else {
                Task task = null;
                String remaining = input.split(" ", 2)[1];
                if(first_word.equals("todo")) {
                    task = new Task.Todo(remaining);

                } else if(first_word.equals("deadline")) {
                    String[] temp = remaining.split("by", 2);
                    System.out.println(temp[1]);
                    task = new Task.Deadline(temp[0], temp[1]);

                } else if(first_word.equals("event")) {
                    String[] temp = remaining.split("at", 2);
                    task = new Task.Event(temp[0], temp[1]);
                } else {
                    task = new Task(input);
                }
                list.add(task);
                System.out.println("Added: " + task.getTaskType() + task.getStatusIcon() + " " + task.getDescription());
                System.out.println("There are " + list.size() + " tasks in the list");
            }
        }
    }
}
