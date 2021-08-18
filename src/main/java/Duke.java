import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String welcomeLine = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String goodbyeLine = "Bye. Hope to see you again soon!";
        System.out.println(welcomeLine);
        boolean isRunning = true;
        String input;
        Task[] listArray = new Task[100];
        int count = 0;
        int index;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.nextLine();
            System.out.println(input);
            if (input.equals("bye")) { //Shuts down the bot when it gets a "bye" prompt
                isRunning = false;
                System.out.println(goodbyeLine);
            } else if (input.equals("list")) { //Returns the list when it gets a "list" prompt
                for (int i = 0; i < count; i++) {
                    System.out.println(i+1 + ". " + listArray[i]);
                }
            } else if (input.matches("done\\s[0-9]+$")) { //Using regex to match the case to get the "done x" prompt and updates the task as well.
                index = Integer.parseInt(input.split(" ")[1]);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(listArray[index-1].done());
            } else {
                listArray[count++] = new Task(input);
                System.out.println("added: " +  input);
            }
        } while (isRunning);
    }
}
