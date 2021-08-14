import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Task[] storedTasks = new Task[100];
        int idx = 0;
        String echoInput = sc.nextLine();
        while (!echoInput.equals("bye")) {
            if (echoInput.equals("list")) {
                for (int i = 0; i < idx; i++) {
                    System.out.println(String.format("%d.%s", i, storedTasks[i].toString()));
                }
            } else if (echoInput.startsWith("done")) {
                try {
                    int num = Integer.parseInt(echoInput.substring(5));
                    storedTasks[num - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(storedTasks[num - 1].toString());
                } catch (NumberFormatException nfe) {
                    System.out.println("done came with an incorrect input");
                } catch (ArrayIndexOutOfBoundsException ae) {
                    System.out.println("number provided with done must be from 1 to 100 inclusive");
                }
            } else {
                storedTasks[idx] = new Task(echoInput);
                System.out.println("added: " + echoInput);
                idx++;
            }
            echoInput = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
