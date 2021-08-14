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
            } else if (echoInput.startsWith("deadline")) {
                int separator = findIndex(echoInput, '/');
                String description = echoInput.substring(9, separator);
                String by = echoInput.substring(separator + 4);
                System.out.println("Got it. I've added this task:");
                storedTasks[idx] = new Deadline(description, by);
                System.out.println(storedTasks[idx].toString());
                idx++;
                System.out.println("Now you have " + idx + " tasks in the list.");
            } else if (echoInput.startsWith("todo")) {
                String description = echoInput.substring(5);
                System.out.println("Got it. I have added this task:");
                storedTasks[idx] = new Todo(description);
                System.out.println(storedTasks[idx].toString());
                idx++;
                System.out.println("Now you have " + idx + " tasks in the list.");
            } else if (echoInput.startsWith("event")) {
                int separator = findIndex(echoInput, '/');
                String description = echoInput.substring(6, separator);
                String at = echoInput.substring(separator + 4);
                System.out.println("Got it. I've added this task:");
                storedTasks[idx] = new Event(description, at);
                System.out.println(storedTasks[idx].toString());
                idx++;
                System.out.println("Now you have " + idx + " tasks in the list.");
            } else {
                storedTasks[idx] = new Task(echoInput);
                System.out.println("added: " + echoInput);
                idx++;
            }
            echoInput = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    //returns -1 if character not found in String
    private static int findIndex(String s, Character c) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }
}
