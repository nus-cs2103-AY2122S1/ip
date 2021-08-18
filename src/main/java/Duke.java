import java.util.ArrayList;
import java.util.Scanner;

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

        ArrayList<String> dataStore = new ArrayList<String>();
        ArrayList<Boolean> status = new ArrayList<Boolean>();

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("");
            String input = sc.nextLine();
    
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < dataStore.size(); i++) {
                    if (status.get(i).equals(true)) {
                        System.out.println(i+1 + ". [X] " + dataStore.get(i));
                    } else {
                        System.out.println(i+1 + ". [ ] " + dataStore.get(i));
                    }
                }
            } else if (input.contains("done")) {
                int idx = Integer.parseInt(input.split(" ")[1]);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[X] " + dataStore.get(idx));
                status.set(idx-1, true);
            } else {
                dataStore.add(input);
                status.add(false);
                System.out.println("added: " + input);
            }
        }

    }
}
