import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // handle greetings
        String greetings = "Hello! I'm Grace" + "\n" + "How can I help you?";
        System.out.println(greetings);

        //creating the list for task
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> done_check = new ArrayList<String>();
        int task_number;

        //loop for multiple scanning
        int quit = 0;
        while (quit == 0) {
            String next_line = scan.nextLine();
            
            // exit if bye
            System.out.println(next_line.substring(0,4));
            if (next_line.equals("bye")) {
                System.out.println("Bye bye! Hope to see you again soon!");
                quit = 1;
            }

            // outputting the list
            else if (next_line.equals("list")) {
                int count = 1;
                System.out.println("Do these soon:" + "\n");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(count + ". [" + done_check.get(i) + "] " + list.get(i));
                    count = count + 1;
                }
            }

            //marking task as done
            else if (next_line.substring(0,4).equals("done")) {
                System.out.println("Yay! you have finished this task!");
                task_number = Integer.valueOf(next_line.substring(5)) - 1;
                done_check.set(task_number, "X");
                System.out.println("[" + done_check.get(task_number) + "] " + list.get(task_number));
            }

            //storing new task into list and mark as not done
            else {
                list.add(next_line);
                done_check.add("");
                System.out.println("added: " + next_line);
            }
        }
    }
}
