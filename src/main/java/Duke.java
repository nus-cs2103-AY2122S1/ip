import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // handle greetings
        String greetings = "Hello! I'm Grace" + "\n" + "How can I help you?";
        System.out.println(greetings);

        //creating the list for task
        ArrayList<Task> list = new ArrayList<Task>();
        ArrayList<String> done_check = new ArrayList<String>();
        int total = 0;
        int task_number;

        //loop for multiple scanning
        int quit = 0;
        while (quit == 0) {
            String next_line = scan.nextLine();

            // exit if bye
            if (next_line.equals("bye")) {
                System.out.println("Bye bye! Hope to see you again soon!");
                quit = 1;
            }

            // outputting the list
            else if (next_line.equals("list")) {
                int count = 1;
                System.out.println("Do these soon:" + "\n");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(count + ". [" + list.get(i).get_type() + "][" + done_check.get(i) + "] " + list.get(i).get_task());
                    count = count + 1;
                }
            }

            //marking task as done
            else if (next_line.substring(0,4).equals("done")) {
                System.out.println("Yay! you have finished this task!");
                task_number = Integer.valueOf(next_line.substring(5)) - 1;
                done_check.set(task_number, "X");
                System.out.println("[" + list.get(task_number).get_type() + "][" + done_check.get(task_number) + "] " + list.get(task_number).get_task());
            }

            //storing new task into list and mark as not done
            else {
                total = total + 1;
                if (next_line.substring(0, 1).equals("t")) {
                    Task todo = new Todo(next_line);
                    list.add(todo);
                    done_check.add("");
                    System.out.println("Added the task! :)");
                    System.out.println("[" + todo.get_type() + "][] " + todo.get_task());
                    System.out.println("Jiayou! you have " + total + " tasks in the list.");
                } else if (next_line.substring(0, 1).equals("d")) {
                    Task deadline = new Deadline(next_line);
                    list.add(deadline);
                    done_check.add("");
                    System.out.println("Added the task! :)");
                    System.out.println("[" + deadline.get_type() + "][] " + deadline.get_task());
                    System.out.println("Jiayou! you have " + total + " tasks in the list.");
                } else {
                    Task event = new Event(next_line);
                    list.add(event);
                    done_check.add("");
                    System.out.println("Added the task! :)");
                    System.out.println("[" + event.get_type() + "][] " + event.get_task());
                    System.out.println("Jiayou! you have " + total + " tasks in the list.");
                }

            }
        }
    }
}
