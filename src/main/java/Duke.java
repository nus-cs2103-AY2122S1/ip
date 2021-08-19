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

        //loop for multiple scanning
        int quit = 0;
        while (quit == 0) {
            String next_line = scan.nextLine();
            // exit if bye
            if (next_line.equals("bye")) {
                System.out.println("Bye bye! Hope to see you again soon!");
                quit = 1;
            }
            // creating list
            else if (next_line.equals("list")) {
                int count = 1;
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(count + ". " + list.get(i) + "\n");
                    count = count + 1;
                }
            }
            //storing into list
            else {
                list.add(next_line);
                System.out.println("added: " + next_line);
            }
        }
    }
}
